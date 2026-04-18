Feature: Customer SOAP Service

  Scenario: Retrieve existing customer by document number
    Given a customer exists with document number "12345678"
    When the client requests the customer by document number "12345678"
    Then the customer full name should be returned as "John Doe"
