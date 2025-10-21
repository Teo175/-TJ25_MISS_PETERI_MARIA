package com.example.demo;


import java.math.BigDecimal;

public class LargeDiscountEvent {
    private final String customerName;
    private final BigDecimal amount;

    public LargeDiscountEvent(String customerName, BigDecimal amount) {
        this.customerName = customerName;
        this.amount = amount;
    }

    public String getCustomerName() { return customerName; }
    public BigDecimal getAmount() { return amount; }
}
