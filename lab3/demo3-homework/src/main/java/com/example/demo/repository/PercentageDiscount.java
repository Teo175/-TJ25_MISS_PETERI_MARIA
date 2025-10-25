package com.example.demo.repository;

import com.example.demo.models.Customer;
import com.example.demo.models.Order;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Service;
import java.math.BigDecimal;

@Service
@ConditionalOnProperty(name = "discount.type", havingValue = "percentage")
public class PercentageDiscount implements DiscountService {

    @Override
    public BigDecimal calculate(Customer customer, Order order) {
        return order.getAmount().multiply(new BigDecimal("0.10")); // 10%
    }

}
