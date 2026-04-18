package com.core.banking.service;

import com.core.banking.domain.Customer;
import com.core.banking.repository.CustomerRepository;
import org.junit.jupiter.api.Test;
import java.time.LocalDate;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class CustomerServiceTest {

    @Test
    void shouldReturnCustomerWhenDocumentExists() {
        // Mocking the repository
        CustomerRepository repository = mock(CustomerRepository.class);

        // Instantiating a customer sample
        Customer customer = new Customer(
                "John Doe",
                "12345678",
                LocalDate.of(1990, 1, 1)
        );

        when(repository.findByDocumentNumber("12345678"))
                .thenReturn(Optional.of(customer));

        CustomerService service = new CustomerService(repository);

        Optional<Customer> result = service.findCustomerByDocument("12345678");

        assertTrue(result.isPresent());
        assertEquals("John Doe", result.get().getFullName());
    }
}
