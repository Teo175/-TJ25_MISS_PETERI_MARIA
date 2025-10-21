package com.example.demo.repository;

import com.example.demo.models.Customer;
import com.example.demo.models.Order;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Service;
import java.math.BigDecimal;

@Service
@ConditionalOnProperty(name = "discount.type", havingValue = "fixed")
public class FixedDiscount implements DiscountService {

    @Override
    public BigDecimal calculate(Customer customer, Order order) {
        if (order.getAmount().compareTo(new BigDecimal("1000")) >= 0) {
            return new BigDecimal("100");
        }
        return BigDecimal.ZERO;
    }
}
