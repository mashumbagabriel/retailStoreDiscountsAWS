package com.generic.retailStoreDiscounts.models;

import lombok.AllArgsConstructor;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Setter
@Getter
@AllArgsConstructor
public class Item {

    private ItemType type;
    private BigDecimal price;
}
