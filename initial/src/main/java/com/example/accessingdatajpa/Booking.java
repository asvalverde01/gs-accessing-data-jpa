package com.example.accessingdatajpa;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;

@Entity
public class Booking {
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String bookingDetails;

    @ManyToOne
    private Customer customer;

    protected Booking() {}

    public Booking(String bookingDetails, Customer customer) {
        this.bookingDetails = bookingDetails;
        this.customer = customer;
    }

    public Long getId() {
        return id;
    }

    public String getBookingDetails() {
        return bookingDetails;
    }

    public Customer getCustomer() {
        return customer;
    }

}
