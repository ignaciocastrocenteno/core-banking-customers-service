package com.core.banking.api.soap.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * SOAP request for retrieving a customer by document number.
 */
@Data
@NoArgsConstructor
public class GetCustomerByDocumentRequest {

    private String documentNumber;
}
