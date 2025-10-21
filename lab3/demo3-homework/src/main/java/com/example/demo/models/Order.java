package com.example.demo.models;
import java.math.BigDecimal;
public class Order {
    private Long id;
    private Long customerId;
    private BigDecimal amount;
    private BigDecimal discount = BigDecimal.ZERO;

    public Order(Long id, Long customerId, BigDecimal amount) {
        this.id = id;
        this.customerId = customerId;
        this.amount = amount;
    }

    public Long getId() { return id; }
    public Long getCustomerId() { return customerId; }
    public BigDecimal getAmount() { return amount; }
    public BigDecimal getDiscount() { return discount; }
    public void setDiscount(BigDecimal discount) { this.discount = discount; }
}
