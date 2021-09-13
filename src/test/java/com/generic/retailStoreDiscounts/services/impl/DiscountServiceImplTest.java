package com.generic.retailStoreDiscounts.services.impl;

import com.generic.retailStoreDiscounts.models.*;
import com.generic.retailStoreDiscounts.services.DiscountService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

@SpringBootTest
@RunWith(SpringRunner.class)

@TestPropertySource(locations = "classpath:application.properties")

public class DiscountServiceImplTest {

    @Autowired
    DiscountService discountService;

    @Test
    public void testCalculateTotal_GroceriesOnly() {
        List<Item> items = new ArrayList<Item>();
        items.add(new Item(ItemType.GROCERY, new BigDecimal(100.0)));
        items.add(new Item(ItemType.GROCERY, new BigDecimal(100.0)));
        items.add(new Item(ItemType.GROCERY, new BigDecimal(100.0)));
        
        BigDecimal total = discountService.calculateTotalPerType(items, ItemType.GROCERY);
        assertEquals(300.00, total.doubleValue(), 0);
    }

    @Test
    public void testCalculateTotalNonGroceriesOnly() {
        List<Item> items = new ArrayList<Item>();
        items.add(new Item(ItemType.CLOTHES, new BigDecimal(100.0)));
        items.add(new Item(ItemType.OTHER, new BigDecimal(100.0)));
        items.add(new Item(ItemType.TECHNOLOGY, new BigDecimal(100.0)));

        
        BigDecimal total = discountService.calculateTotal(items);
        assertEquals(300.00, total.doubleValue(), 0);
    }

    @Test
    public void testCalculateTotalMix() {
        List<Item> items = new ArrayList<Item>();
        items.add(new Item(ItemType.CLOTHES, new BigDecimal(100.0)));
        items.add(new Item(ItemType.OTHER, new BigDecimal(100.0)));
        items.add(new Item(ItemType.TECHNOLOGY, new BigDecimal(100.0)));
        items.add(new Item(ItemType.GROCERY, new BigDecimal(100.0)));
        items.add(new Item(ItemType.GROCERY, new BigDecimal(100.0)));

        
        BigDecimal total = discountService.calculateTotalPerType(items, ItemType.GROCERY);
        assertEquals(200.00, total.doubleValue(), 0);
    }

    @Test
    public void testCalculateDiscount_10pct() {
        
        BigDecimal total = discountService.calculateDiscount(new BigDecimal(1000), new BigDecimal(0.1));
        assertEquals(900.00, total.doubleValue(), 0);
    }

    @Test
    public void testCalculateDiscount_50pct() {
        
        BigDecimal total = discountService.calculateDiscount(new BigDecimal(1000), new BigDecimal(0.5));
        assertEquals(500.00, total.doubleValue(), 0);
    }

    @Test
    public void testCalculateDiscount_0pct() {
        
        BigDecimal total = discountService.calculateDiscount(new BigDecimal(1000),  new BigDecimal(0.0));
        assertEquals(1000.00, total.doubleValue(), 0);
    }

    @Test
    public void testCalculateDiscount_100pct() {
        
        BigDecimal total = discountService.calculateDiscount(new BigDecimal(1000),  new BigDecimal(1.0));
        assertEquals(0.0, total.doubleValue(), 0);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testCalculateDiscount_error() {
        
        discountService.calculateDiscount(new BigDecimal(1000),  new BigDecimal(2.0));
    }

    @Test
    public void testGetUserSpecificDiscount_affiliate() {
        User user = new User(UserType.AFFILIATE, LocalDate.now());
        
        BigDecimal discount = discountService.getUserDiscount(user);
        assertEquals(0.1, discount.doubleValue(), 0);
    }

    @Test
    public void testGetUserSpecificDiscount_employee() {
        User user = new User(UserType.EMPLOYEE, LocalDate.now());
        
        BigDecimal discount = discountService.getUserDiscount(user);
        assertEquals(0.3, discount.doubleValue(), 0);
    }

    @Test
    public void testGetUserSpecificDiscount_customer_old() {
        LocalDate joinDate = LocalDate.of(2016, 2, 23);
        User user = new User(UserType.OVER_TWO_YEARS_CUSTOMER, joinDate);
        
        BigDecimal discount = discountService.getUserDiscount(user);
        assertEquals(0.05, discount.doubleValue(), 0);
    }

    @Test
    public void testGetUserSpecificDiscount_customer_new() {
        User user = new User(UserType.OVER_TWO_YEARS_CUSTOMER, LocalDate.now());
        
        BigDecimal discount = discountService.getUserDiscount(user);
        assertEquals(0.05, discount.doubleValue(), 0);
    }

    @Test(expected = NullPointerException.class)
    public void testGetUserSpecificDiscount_customer_null_user() {
        
        discountService.getUserDiscount(null);
    }

    @Test
    public void testIsCustomerSince() {
        
        LocalDate joinDate = LocalDate.now();
        boolean isTwoYearsJoined = discountService.isCustomerSince(joinDate, 2);
        assertFalse(isTwoYearsJoined);
    }

    @Test
    public void testIsCustomerSince_1year() {
        
        LocalDate joinDate = LocalDate.now().minusYears(1);
        boolean isTwoYearsJoined = discountService.isCustomerSince(joinDate, 2);
        assertFalse(isTwoYearsJoined);
    }

    @Test
    public void testIsCustomerSince_2years() {
        
        LocalDate joinDate = LocalDate.now().minusYears(2);
        boolean isTwoYearsJoined = discountService.isCustomerSince(joinDate, 2);
        assertTrue(isTwoYearsJoined);
    }

    @Test
    public void testIsCustomerSince_3years() {
        
        LocalDate joinDate = LocalDate.now().minusYears(3);
        boolean isTwoYearsJoined = discountService.isCustomerSince(joinDate, 2);
        assertTrue(isTwoYearsJoined);
    }

    @Test
    public void testCalculateBillsDiscount() {
        
        BigDecimal amount = discountService.calculateBillsDiscount(new BigDecimal(1000),  new BigDecimal(100),  new BigDecimal(5));
        assertEquals(50, amount.doubleValue(), 0);
    }

    @Test
    public void testCalculateBillsDiscount_2() {
        
        BigDecimal amount = discountService.calculateBillsDiscount(new BigDecimal(1000),  new BigDecimal(50),  new BigDecimal(5));
        assertEquals(100, amount.doubleValue(), 0);
    }

    @Test
    public void testCalculateBillsDiscount_3() {
        
        BigDecimal amount = discountService.calculateBillsDiscount( new BigDecimal(5632), new BigDecimal(100), new BigDecimal(5));
        assertEquals(280, amount.doubleValue(), 0);
    }

    @Test
    public void testDiscountServiceCalculate() {
        List<Item> items = new ArrayList<Item>();
        items.add(new Item(ItemType.GROCERY, new BigDecimal(50.0)));
        items.add(new Item(ItemType.TECHNOLOGY, new BigDecimal(200.0)));
        items.add(new Item(ItemType.GROCERY, new BigDecimal(10.0)));

        Basket basket = new Basket();
        basket.setItems(items);

        discountService.discountCalculation(new User(UserType.OVER_TWO_YEARS_CUSTOMER, LocalDate.now()), basket);
        
        BigDecimal amount = discountService.calculateBillsDiscount(new BigDecimal(5632), new BigDecimal(100), new BigDecimal(5));
        assertEquals(280, amount.doubleValue(), 0);
    }
}
