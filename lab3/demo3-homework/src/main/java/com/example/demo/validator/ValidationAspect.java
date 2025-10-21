package com.example.demo.validator;

import com.example.demo.models.Customer;
import com.example.demo.models.Order;
import com.example.demo.repository.CustomerRepository;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class ValidationAspect {

    private final CustomerRepository repository;

    public ValidationAspect(CustomerRepository repository) {
        this.repository = repository;
    }

    @Around("@annotation(ValidateCustomer)")
    public Object validate(ProceedingJoinPoint joinPoint) throws Throwable {
        Order order = (Order) joinPoint.getArgs()[0];

        Customer customer = repository.findById(order.getCustomerId())
                .orElseThrow(() -> new RuntimeException("Customer not found"));

        if (!customer.isEligible()) {
            throw new RuntimeException("Customer " + customer.getName() + " not eligible");
        }

        return joinPoint.proceed();
    }
}
