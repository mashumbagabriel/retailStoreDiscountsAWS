package com.generic.theRetailStoreDiscounts.models;

public enum UserType {
    AFFILIATE(0.10),EMPLOYEE(0.30),CUSTOMER(0.05);

    private double discountPercentage;

    UserType(double discountPercentage) {
        this.discountPercentage = discountPercentage;
    }

    public double getDiscountPercentage() {
        return discountPercentage;
    }
}
