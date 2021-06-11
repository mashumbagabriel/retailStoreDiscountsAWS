package com.generic.theRetailStoreDiscounts.services;

import com.generic.theRetailStoreDiscounts.models.Bill;
import com.generic.theRetailStoreDiscounts.models.User;

import java.math.BigDecimal;

public interface DiscountService {

    BigDecimal discountCalculation(User user, Bill bill);
}
