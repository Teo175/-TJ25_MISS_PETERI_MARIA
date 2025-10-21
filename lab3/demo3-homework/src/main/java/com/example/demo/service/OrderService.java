package com.example.demo.service;


import com.example.demo.LargeDiscountEvent;
import com.example.demo.models.Customer;
import com.example.demo.models.Order;
import com.example.demo.repository.CustomerRepository;
import com.example.demo.repository.DiscountService;
import com.example.demo.validator.ValidateCustomer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
import java.math.BigDecimal;

@Service
public class OrderService {
    private static final Logger log = LoggerFactory.getLogger(OrderService.class);

    private final DiscountService discountService;
    private final CustomerRepository customerRepository;
    private final ApplicationEventPublisher eventPublisher;

    public OrderService(DiscountService discountService,
                        CustomerRepository customerRepository,
                        ApplicationEventPublisher eventPublisher) {
        this.discountService = discountService;
        this.customerRepository = customerRepository;
        this.eventPublisher = eventPublisher;
    }

    @ValidateCustomer
    public Order process(Order order) {
        Customer customer = customerRepository.findById(order.getCustomerId()).orElseThrow();
        BigDecimal discount = discountService.calculate(customer, order);
        order.setDiscount(discount);

        log.info("Applied discount - Method: {}, Customer: {}, Amount: {}",
                discountService.getClass().getSimpleName(), customer.getName(), discount);

        if (discount.compareTo(new BigDecimal("50")) > 0) {
            eventPublisher.publishEvent(new LargeDiscountEvent(customer.getName(), discount));
        }

        return order;
    }
}
