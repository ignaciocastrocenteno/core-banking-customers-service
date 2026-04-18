package com.core.banking.endpoint;

import com.core.banking.domain.Customer;
import com.core.banking.service.CustomerService;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class CustomerEndpointTest {

    @Test
    void shouldReturnCustomerResponseWhenCustomerExists() {

        CustomerService service = mock(CustomerService.class);
        CustomerEndpoint endpoint = new CustomerEndpoint(service);

        Customer customer = new Customer(
                "Jane Doe",
                "98765432",
                LocalDate.of(1985, 5, 20)
        );

        when(service.findCustomerByDocument("98765432"))
                .thenReturn(Optional.of(customer));

        var request = new com.core.banking.api.soap.request.GetCustomerByDocumentRequest();
        request.setDocumentNumber("98765432");

        var response = endpoint.getCustomerByDocument(request);

        assertNotNull(response.getCustomer());
        assertEquals("Jane Doe", response.getCustomer().getFullName());
    }
}
