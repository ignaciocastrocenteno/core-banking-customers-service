package com.core.banking.endpoint;

import com.core.banking.api.soap.dto.CustomerDto;
import com.core.banking.api.soap.request.GetCustomerByDocumentRequest;
import com.core.banking.api.soap.response.GetCustomerByDocumentResponse;
import com.core.banking.domain.Customer;
import com.core.banking.service.CustomerService;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

import java.util.Optional;

/**
 * SOAP endpoint for customer-related operations
 */
@Endpoint
public class CustomerEndpoint {

    private static final String NAMESPACE_URI = "http://core.banking/customer";

    private final CustomerService customerService;

    public CustomerEndpoint(CustomerService customerService) {
        this.customerService = customerService;
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "GetCustomerByDocumentRequest")
    @ResponsePayload
    public GetCustomerByDocumentResponse getCustomerByDocument(
            @RequestPayload GetCustomerByDocumentRequest request) {

        Optional<Customer> customer = customerService
                .findCustomerByDocument(request.getDocumentNumber());

        return customer
                .map(this::mapToResponse)
                .orElse(new GetCustomerByDocumentResponse(null));
    }

    private GetCustomerByDocumentResponse mapToResponse(Customer customer) {

        CustomerDto dto = new CustomerDto(
                customer.getId(),
                customer.getFullName(),
                customer.getDocumentNumber(),
                customer.getBirthDate()
        );

        return new GetCustomerByDocumentResponse(dto);
    }
}
