package com.core.banking.domain;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Represents a banking customer entity.
 */
@Entity
@Table(name = "customers")
@Data
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 100)
    private String fullName;

    @Column(nullable = false, unique = true, length = 20)
    private String documentNumber;

    @Column(nullable = false)
    private LocalDate birthDate;

    protected Customer() {
        // JPA requires an empty constructor to work
    }

    public Customer(String fullName, String documentNumber, LocalDate birthDate) {
        setFullName(fullName);
        setDocumentNumber(documentNumber);
        setBirthDate(birthDate);
    }

    public Long getId() {
        return id;
    }

    public String getFullName() {
        return fullName;
    }

    public String getDocumentNumber() {
        return documentNumber;
    }

    private LocalDate getBirthDate() {
        return birthDate;
    }

    private void setId(Long id) {
        this.id = id;
    }

    private void setFullName(String fullName) {
        this.fullName = fullName;
    }

    private void setDocumentNumber(String documentNumber) {
        this.documentNumber = documentNumber;
    }

    private void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }
}
