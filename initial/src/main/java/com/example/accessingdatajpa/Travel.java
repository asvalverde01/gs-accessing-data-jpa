package com.example.accessingdatajpa;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import java.util.Set;

@Entity
public class Travel {
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String travelDetails;

    @ManyToOne
    private Customer customer;

    protected Travel() {}

    public Travel(String travelDetails, Customer customer) {
        this.travelDetails = travelDetails;
        this.customer = customer;
    }

    public Long getId() {
        return id;
    }

    public String getTravelDetails() {
        return travelDetails;
    }

    public Customer getCustomer() {
        return customer;
    }
}
