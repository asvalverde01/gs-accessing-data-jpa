package com.example.accessingdatajpa;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;

@RepositoryRestResource(collectionResourceRel = "customers", path = "customers")
public interface CustomerRepository extends CrudRepository<Customer, Long> {

    @RestResource(path = "by-name", rel = "by-name")
    List<Customer> findByLastName(@Param("name") String lastName);

    Customer findById(long id);
}

/* http://localhost:8080/people/search/findByLastName?name=Bauer */