package com.customer.customer_servive.repository;

import com.customer.customer_servive.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CustomerRepository extends JpaRepository<Customer,Long> {
    Optional<Customer>findByEmail(String email);
}
