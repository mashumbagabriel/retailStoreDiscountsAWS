package com.generic.theRetailStoreDiscounts.services.impl;

import com.generic.theRetailStoreDiscounts.helper.DiscountHelper;
import com.generic.theRetailStoreDiscounts.models.Bill;
import com.generic.theRetailStoreDiscounts.models.ItemType;
import com.generic.theRetailStoreDiscounts.models.User;
import com.generic.theRetailStoreDiscounts.services.DiscountService;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;

@Service
public class DiscountServiceImpl implements DiscountService {

    private static final int DISCOUNT_PER_HUNDRED = 5;

	@Override
	public BigDecimal discountCalculation(User user, Bill bill) {
		DiscountHelper helper = new DiscountHelper();

        BigDecimal totalAmount = helper.calculateTotal(bill.getItems());
        BigDecimal groceryAmount = helper.calculateTotalPerType(bill.getItems(), ItemType.GROCERY);
        BigDecimal nonGroceryAmount = totalAmount.subtract(groceryAmount);
        BigDecimal userDiscount = helper.getUserDiscount(user);
        BigDecimal billsDiscount = helper.calculateBillsDiscount(totalAmount, new BigDecimal(100), new BigDecimal(DISCOUNT_PER_HUNDRED));
		if (nonGroceryAmount.compareTo(BigDecimal.ZERO) > 0) {
			nonGroceryAmount = helper.calculateDiscount(nonGroceryAmount, userDiscount);
		}

        BigDecimal finalAmount = (groceryAmount.add(nonGroceryAmount).subtract(billsDiscount));

		return finalAmount.setScale(2, RoundingMode.HALF_UP);
	}
}
