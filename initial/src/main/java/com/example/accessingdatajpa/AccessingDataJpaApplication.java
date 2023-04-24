package com.example.accessingdatajpa;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class AccessingDataJpaApplication {

  private static final Logger log = LoggerFactory.getLogger(AccessingDataJpaApplication.class);

  public static void main(String[] args) {
    SpringApplication.run(AccessingDataJpaApplication.class, args);
  }

  @Bean
  public CommandLineRunner demo(CustomerRepository customerRepository, BookingRepository bookingRepository, TravelRepository travelRepository) {
    return (args) -> {
      // save a few customers
      Customer customer1 = new Customer("Jack", "Bauer");
      Customer customer2 = new Customer("Chloe", "O'Brian");
      Customer customer3 = new Customer("Kim", "Bauer");
      Customer customer4 = new Customer("David", "Palmer");
      Customer customer5 = new Customer("Michelle", "Dessler");

      customerRepository.save(customer1);
      customerRepository.save(customer2);
      customerRepository.save(customer3);
      customerRepository.save(customer4);
      customerRepository.save(customer5);

      // save a few bookings
      Booking booking1 = new Booking("Booking1", customer1);
      Booking booking2 = new Booking("Booking2", customer2);
      Booking booking3 = new Booking("Booking3", customer3);
      Booking booking4 = new Booking("Booking4", customer4);
      Booking booking5 = new Booking("Booking5", customer5);

      bookingRepository.save(booking1);
      bookingRepository.save(booking2);
      bookingRepository.save(booking3);
      bookingRepository.save(booking4);
      bookingRepository.save(booking5);

      // save a few travels
      Travel travel1 = new Travel("Travel1", customer1);
      Travel travel2 = new Travel("Travel2", customer2);
      Travel travel3 = new Travel("Travel3", customer3);
      Travel travel4 = new Travel("Travel4", customer4);
      Travel travel5 = new Travel("Travel5", customer5);

      travelRepository.save(travel1);
      travelRepository.save(travel2);
      travelRepository.save(travel3);
      travelRepository.save(travel4);
      travelRepository.save(travel5);

      // fetch all customers
      log.info("Customers found with findAll():");
      log.info("-------------------------------");
      for (Customer customer : customerRepository.findAll()) {
        log.info(customer.toString());
      }
      log.info("");

      // fetch an individual customer by ID
      Customer customer = customerRepository.findById(1L);
      log.info("Customer found with findById(1L):");
      log.info("--------------------------------");
      log.info(customer.toString());
      log.info("");

      // fetch customers by last name
      log.info("Customer found with findByLastName('Bauer'):");
      log.info("--------------------------------------------");
      customerRepository.findByLastName("Bauer").forEach(bauer -> {
        log.info(bauer.toString());
      });
      // for (Customer bauer : repository.findByLastName("Bauer")) {
      //  log.info(bauer.toString());
      // }
      log.info("");
    };
  }

}