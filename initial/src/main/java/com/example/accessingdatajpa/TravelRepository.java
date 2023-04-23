package com.example.accessingdatajpa;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;


@RepositoryRestResource(collectionResourceRel = "travels", path = "travels")
public interface TravelRepository extends CrudRepository<Travel, Long> {
    
    // Find all travels
    List<Travel> findAll();
    
    // Find a travel by ID
    Travel findById(long id);
    
    // Find all travels for a specific customer
    List<Travel> findByCustomer(Customer customer);
    
    // Find all travels with details containing a specific string
    List<Travel> findByTravelDetailsContaining(String keyword);
    
    // Save a new travel
    Travel save(Travel travel);
    
    // Delete a travel by ID
    void deleteById(long id);
    

}