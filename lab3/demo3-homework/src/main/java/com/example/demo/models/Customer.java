package com.example.demo.models;

public class Customer {
    private Long id;
    private String name;
    private boolean eligible;

    public Customer(Long id, String name, boolean eligible) {
        this.id = id;
        this.name = name;
        this.eligible = eligible;
    }

    public Long getId() { return id; }
    public String getName() { return name; }
    public boolean isEligible() { return eligible; }
}
