# Core Banking Customers SOAP Service

## Overview

This project implements a **SOAP-based Customer Information Service** designed for **legacy Core Banking environments** where modern REST/JSON architectures are not feasible. The service exposes banking customer data via **XML/SOAP 1.1**, following a **contract-first approach** and ensuring long-term stability, backward compatibility, and strict separation of concerns.

The solution is intentionally aligned with **enterprise and banking-grade standards**, emphasizing predictability, auditability, clear contracts, and extensive testing practices.

---

## Project Goals

- Provide a stable SOAP API for querying banking customer data
- Ensure full compatibility with **JDK 1.8** and legacy consumers
- Apply **Clean Architecture** and **SOLID principles**
- Promote maintainability, testability, and scalability
- Enable functional, unit, BDD, and performance testing

---

## High-Level Architecture

The project follows a layered architecture inspired by **Clean Architecture** and adapted for Spring-based enterprise systems:

```bash
SOAP Transport Layer (Spring-WS Endpoints)
                    ↓
Application Layer (Use Cases / Services)
                    ↓
Domain Layer (JPA Entities, Business Rules)
                    ↓
Infrastructure Layer (Repositories, Database Access)
```

### Architectural Characteristics

- Contract-first SOAP design (XSD-driven)
- Domain model isolated from transport concerns
- Explicit mappings between domain entities and DTOs
- No framework leakage into the domain layer

---

## Technologies, Frameworks & Tools

### Core Stack

| Component  | Technology                         | Version      |
| ---------- | ---------------------------------- | ------------ |
| Language   | Java                               | 1.8          |
| Framework  | Spring Framework (not Spring Boot) | 5.3.33       |
| SOAP       | Spring Web Services                | 3.1.1        |
| ORM        | Hibernate                          | 5.6.15.Final |
| JPA        | Spring Data JPA                    | 2.7.18       |
| Database   | PostgreSQL                         | 42.7.3       |
| Build Tool | Maven                              | 3.x          |

### Supporting Libraries

| Purpose                             | Library  | Version |
| ----------------------------------- | -------- | ------- |
| XML Binding                         | JAXB API | 2.3.1   |
| Boilerplate Reduction (Annotations) | Lombok   | 1.18.32 |

### Testing & Quality

| Area         | Tool          | Version |
| ------------ | ------------- | ------- |
| Unit Testing | JUnit Jupiter | 5.10.2  |
| Mocking      | Mockito       | 5.x     |
| BDD          | Cucumber      | 7.15.0  |
| Performance  | Apache JMeter | 5.6.3   |

---

## Lombok Usage Policy

Lombok is intentionally **restricted** to non-core areas of the system.

### Allowed Usage

- SOAP DTOs
- SOAP Request and Response objects
- Builders
- Test utilities

### Disallowed Usage

- JPA entities
- Domain models
- Core application services

### Rationale

This policy prevents:

- Hibernate proxy and lazy-loading issues
- Accidental `equals`, `hashCode`, and `toString` misuse
- Debugging and tracing difficulties
- Tooling incompatibilities in regulated banking environments

---

## SOAP Contract Definition

The service exposes one main operation:

### GetCustomerByDocument

- **Input:** Customer document number
- **Output:** Customer information (if found)

The contract is defined in `customer.xsd` and serves as the **single source of truth** for all consumers.

### Namespace

http://core.banking/customer

### WSDL Exposure

Once the application is running, the WSDL can be accessed at:
http://localhost:8080/ws/customers.wsdl

---

## Project Structure

```bash
core-banking-customers-service
    │
    ├── pom.xml
    ├── README.md
    ├── src/main/java/com/core/banking
    │ ├── config # Spring and SOAP configuration
    │ ├── endpoint # SOAP endpoints
    │ ├── service # Application services (use cases)
    │ ├── domain # JPA entities and business rules
    │ └── repository # Persistence adapters
    │
    ├── src/main/resources
    │ └── xsd       # SOAP contract definitions
    │
    ├── src/test        # JUnit & Cucumber tests (unit & BDD tests, respectively)
    │ ├── com.core.banking
    │ │ ├── endpoint
    │ │ │       └── CustomerEndpointTest.java
    │ │ └── service
    │ │         └── CustomerServiceTest.java
    │
    ├── jmeter      # Performance testing artifacts
    │ ├── test-plans
    │ ├── data
    │ └── README.md
    │
    └── README.md
```

---

## Testing Strategy

### Unit Testing (JUnit)

- Tests application services and SOAP endpoints
- Fully isolated using mocks
- Focus on deterministic and reproducible behavior

### BDD Testing (Cucumber)

- Uses Gherkin feature files
- Validates system behavior from a business perspective
- Promotes readability, reuse, and maintainability

### Performance Testing (JMeter)

- SOAP load testing with real XML envelopes
- CSV-driven datasets
- Measurement of latency, throughput, and error rates
- Designed to simulate core banking workloads

---

## How to Run the Project Locally

### Prerequisites

- Java JDK 1.8
- Maven 3.x
- PostgreSQL
- Apache JMeter (for performance testing)

### Steps

1. Clone the repository
2. Configure the PostgreSQL datasource in `application.yml`
3. Build the project:
   ```bash
   mvn clean install
   ```

## Software Engineering Practices Applied

- **SOLID Principles** – to ensure extensibility and maintainability
- **Clean Architecture** – to decouple core logic from frameworks
- **KISS** – simple and explicit implementations
- **DRY** – no duplicated logic
- **YAGNI** – only required features implemented

These practices are essential for long-lived and regulated banking systems.

---

## Security & Legacy Compatibility Notes

- SOAP 1.1 compliant
- XML-based payloads only (no JSON)
- Easily integrable with ESBs and mainframe adapters
- Architecture ready for future WS-Security integration

## Licensing

This project is licensed under the GNU General Public License v3 (GPLv3).

```bash
Copyright (c) 2026
Ignacio Julián Castro Centeno

This program is free software: you can redistribute it and/or modify
it under the terms of the GNU General Public License as published by
the Free Software Foundation, either version 3 of the License, or
(at your option) any later version.

This program is distributed in the hope that it will be useful,
but WITHOUT ANY WARRANTY; without even the implied warranty of
MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.
```

## Author

Ignacio Julián Castro Centeno (Senior Software Engineer), April 2026.

## Final Notes

This repository is intended as a reference-quality implementation of a
SOAP-based Core Banking service, balancing legacy constraints with modern
software engineering discipline.
It is designed to be safely audited, extended, and maintained in regulated
banking environments.
