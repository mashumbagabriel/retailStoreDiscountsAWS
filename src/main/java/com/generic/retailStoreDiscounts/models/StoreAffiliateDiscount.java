package com.generic.retailStoreDiscounts.models;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
@Data
public class StoreAffiliateDiscount extends Discount {

    @Value("${discount.store_affiliated.rate}")
    private BigDecimal discountRate;

    @Override
    public BigDecimal getDiscountRate(){

        return this.discountRate;
    }
}
