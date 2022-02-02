@init
Feature: Authentication API

  Scenario: Authentication API should be called successfully
    Given I call authentication API
    Then should return authentication data
    And status code should be 200

  Scenario: Validate schema
    Given I call authentication API
    When I get API schema
    Then schema should be valid

  Scenario: Authentication API should not be called successfully without header
    Given I call authentication API without header
    Then should return message error
    And status code should be 401

  Scenario: Authentication API should not be called successfully with invalid header
    Given I call authentication API with invalid header
    Then should return message error
    And status code should be 401