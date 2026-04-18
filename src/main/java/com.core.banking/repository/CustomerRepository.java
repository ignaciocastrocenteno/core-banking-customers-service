package com.core.banking.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.core.banking.domain.Customer;

/**
 * Repository abstraction for Customer entity.
 */
public interface CustomerRepository extends JpaRepository<Customer, Long> {

    Optional<Customer> findByDocumentNumber(String documentNumber);
}
