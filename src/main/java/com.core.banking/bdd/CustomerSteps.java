package com.core.banking.bdd;

import com.core.banking.domain.Customer;
import com.core.banking.service.CustomerService;
import io.cucumber.java.Before;
import io.cucumber.java.en.*;

import java.time.LocalDate;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

import static org.mockito.Mockito.*;

public class CustomerSteps {

    private CustomerService customerService;
    private Optional<Customer> response;

    @Before
    public void setup() {
        customerService = mock(CustomerService.class);
    }

    @Given("a customer exists with document number {string}")
    public void a_customer_exists(String documentNumber) {

        Customer customer = new Customer(
                "John Doe",
                documentNumber,
                LocalDate.of(1990, 1, 1)
        );

        when(customerService.findCustomerByDocument(documentNumber))
                .thenReturn(Optional.of(customer));
    }

    @When("the client requests the customer by document number {string}")
    public void request_customer(String documentNumber) {
        response = customerService.findCustomerByDocument(documentNumber);
    }

    @Then("the customer full name should be returned as {string}")
    public void validate_name(String fullName) {
        assertTrue(response.isPresent());
        assertEquals(fullName, response.get().getFullName());
    }
}
