package com.example.demo.repository;


import com.example.demo.models.Customer;
import com.example.demo.models.Order;

import java.math.BigDecimal;

public interface DiscountService {
    BigDecimal calculate(Customer customer, Order order);
}
