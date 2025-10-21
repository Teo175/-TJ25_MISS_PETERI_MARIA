package com.example.demo;


import com.example.demo.models.Order;
import com.example.demo.service.OrderService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import java.math.BigDecimal;

@SpringBootApplication
public class OrderApplication {

    public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(OrderApplication.class, args);

        // Test the system
        OrderService service = context.getBean(OrderService.class);
        Order order = new Order(1L, 1L, new BigDecimal("1200"));
        service.process(order);

        System.out.println("Final amount: " + order.getAmount().subtract(order.getDiscount()));
    }
}
