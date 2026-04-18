package com.core.banking.api.soap.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * SOAP response containing customer information.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class GetCustomerByDocumentResponse {

    private CustomerDto customer;
}
