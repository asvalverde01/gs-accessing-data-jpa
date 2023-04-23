package com.example.accessingdatajpa;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;

@RepositoryRestResource(path = "bookings")
public interface BookingRepository extends CrudRepository<Booking, Long> {

    @RestResource(path = "by-customer", rel = "by-customer")
    List<Booking> findByCustomer(Customer customer);

}
