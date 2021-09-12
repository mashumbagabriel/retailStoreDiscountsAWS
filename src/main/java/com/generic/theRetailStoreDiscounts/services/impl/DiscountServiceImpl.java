package com.generic.theRetailStoreDiscounts.services.impl;

import com.generic.theRetailStoreDiscounts.models.*;
import com.generic.theRetailStoreDiscounts.services.DiscountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.time.Period;
import java.util.List;

@Service
public class DiscountServiceImpl implements DiscountService {

    @Value("${discount.price.amount}")
    private BigDecimal DISCOUNT_PER_HUNDRED;

    @Value("${discount.price.threshhold}")
    private BigDecimal DISCOUNT_THRESH_HOLD;

    @Autowired
    ZeroDiscount zeroDiscount;

    @Autowired
    EmployeeDiscount employeeDiscount;

    @Autowired
    StoreAffiliateDiscount storeAffiliateDiscount;
    @Autowired
    TwoYearsCustomerDiscount twoYearsCustomerDiscount;

    @Override
    public BigDecimal discountCalculation(User user, Basket basket) {

        BigDecimal totalAmount = calculateTotal(basket.getItems());
        BigDecimal groceryAmount = calculateTotalPerType(basket.getItems(), ItemType.GROCERY);
        BigDecimal nonGroceryAmount = totalAmount.subtract(groceryAmount);
        BigDecimal userDiscount = getUserDiscount(user);
        BigDecimal billsDiscount = calculateBillsDiscount(totalAmount, DISCOUNT_THRESH_HOLD, DISCOUNT_PER_HUNDRED);
        if (nonGroceryAmount.compareTo(BigDecimal.ZERO) > 0) {
            nonGroceryAmount = calculateDiscount(nonGroceryAmount, userDiscount);
        }

        BigDecimal finalAmount = (groceryAmount.add(nonGroceryAmount).subtract(billsDiscount));

        return finalAmount.setScale(2, RoundingMode.HALF_UP);
    }

    @Override
    public BigDecimal calculateTotal(List<Item> items) {
        return items.stream().map(i -> i.getPrice()).reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    @Override
    public BigDecimal calculateTotalPerType(List<Item> items, ItemType type) {
        BigDecimal sum = new BigDecimal(0);


        if (type != null) {
            sum = items.stream().filter(i -> type.equals(i.getType())).map(i -> i.getPrice()).reduce(BigDecimal.ZERO, BigDecimal::add);
        }

        return sum;
    }

    @Override
    public BigDecimal getUserDiscount(User user) {

        Discount discount = zeroDiscount;

        UserType type = user.getType();

        switch (type) {
            case EMPLOYEE:
                discount = employeeDiscount;
                break;

            case AFFILIATE:
                discount = storeAffiliateDiscount;
                break;

            case OVER_TWO_YEARS_CUSTOMER:
                discount = twoYearsCustomerDiscount;
                break;

            default:
                break;
        }

        return discount.getDiscountRate();
    }

    @Override
    public boolean isCustomerSince(LocalDate registeredDate, long years) {
        Period period = Period.between(registeredDate, LocalDate.now());
        return period.getYears() >= years;
    }

    @Override
    public BigDecimal calculateBillsDiscount(BigDecimal totalAmount, BigDecimal amount, BigDecimal discountAmount) {
        int value = totalAmount.divide(amount).intValue();
        return discountAmount.multiply(new BigDecimal(value));
    }

    @Override
    public BigDecimal calculateDiscount(BigDecimal amount, BigDecimal discount) {
        if (discount.doubleValue() > 1.0) {
            throw new IllegalArgumentException("Discount cannot be more than 100%");
        }

        BigDecimal x = amount.multiply(discount);
        return amount.subtract(x);
    }
}
