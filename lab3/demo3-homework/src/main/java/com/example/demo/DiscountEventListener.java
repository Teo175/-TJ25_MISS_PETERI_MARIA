package com.example.demo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public class DiscountEventListener {
    private static final Logger log = LoggerFactory.getLogger(DiscountEventListener.class);

    @EventListener
    public void handleLargeDiscount(LargeDiscountEvent event) {
        log.warn("ALERT: Large discount! Customer: {}, Amount: {}",
                event.getCustomerName(), event.getAmount());
    }
}
