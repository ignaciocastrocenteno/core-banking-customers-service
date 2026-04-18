package com.core.banking.dto;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * SOAP DTO representing a banking customer
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CustomerDto {

    private Long id;
    private String fullName;
    private String documentNumber;
    private LocalDate birthDate;
}
