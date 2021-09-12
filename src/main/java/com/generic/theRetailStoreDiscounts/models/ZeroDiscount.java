package com.generic.theRetailStoreDiscounts.models;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
@Data
public class ZeroDiscount extends Discount {

    @Value("${discount.zero_discount.rate}")
    private BigDecimal discountRate;

    @Override
    public BigDecimal getDiscountRate(){

        return this.discountRate;
    }
}
