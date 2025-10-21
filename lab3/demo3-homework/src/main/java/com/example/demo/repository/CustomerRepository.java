package com.example.demo.repository;

import com.example.demo.models.Customer;
import org.springframework.stereotype.Repository;
import java.util.Map;
import java.util.Optional;

@Repository
public class CustomerRepository {
    private final Map<Long, Customer> db = Map.of(
            1L, new Customer(1L, "Alice", true),
            2L, new Customer(2L, "Bob", true),
            3L, new Customer(3L, "Charlie", false)
    );

    public Optional<Customer> findById(Long id) {
        return Optional.ofNullable(db.get(id));
    }
}
