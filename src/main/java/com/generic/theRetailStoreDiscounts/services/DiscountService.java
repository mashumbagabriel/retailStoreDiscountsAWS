package com.generic.theRetailStoreDiscounts.services;

import com.generic.theRetailStoreDiscounts.models.Basket;
import com.generic.theRetailStoreDiscounts.models.Item;
import com.generic.theRetailStoreDiscounts.models.ItemType;
import com.generic.theRetailStoreDiscounts.models.User;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Component
public interface DiscountService {

    BigDecimal discountCalculation(User user, Basket basket);
    BigDecimal calculateTotal(List<Item> items) ;
    boolean isCustomerSince(LocalDate registeredDate, long years);
    BigDecimal calculateBillsDiscount(BigDecimal totalAmount, BigDecimal amount, BigDecimal discountAmount);
    BigDecimal calculateDiscount(BigDecimal amount, BigDecimal discount);
    BigDecimal calculateTotalPerType(List<Item> items, ItemType type);
    BigDecimal getUserDiscount(User user);

}
