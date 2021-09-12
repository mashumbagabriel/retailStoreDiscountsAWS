package com.generic.theRetailStoreDiscounts.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Setter
@Getter
public class DiscountRequest {


    @NotNull(message = "user field is mandatory.")
    @JsonProperty("user")
    private User user;
    @NotNull(message = "basket field is mandatory.")
    @JsonProperty("basket")
    private Basket basket;

}
