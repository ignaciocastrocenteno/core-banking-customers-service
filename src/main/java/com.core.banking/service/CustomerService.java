package com.core.banking.service;

import java.util.Optional;

import com.core.banking.domain.Customer;
import com.core.banking.repository.CustomerRepository;

/**
 * Application service handling customer use cases.
 */
public class CustomerService {

    // Dependency Injection from the repository layer
    private final CustomerRepository customerRepository;

    public CustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    public Optional<Customer> findCustomerByDocument(String documentNumber) {
        return customerRepository.findByDocumentNumber(documentNumber);
    }
}
