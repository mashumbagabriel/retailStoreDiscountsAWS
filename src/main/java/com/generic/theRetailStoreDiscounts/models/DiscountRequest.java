package com.generic.theRetailStoreDiscounts.models;

import com.generic.theRetailStoreDiscounts.models.Bill;
import com.generic.theRetailStoreDiscounts.models.User;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class DiscountRequest {

    private User user;
    private Bill bill;

}
