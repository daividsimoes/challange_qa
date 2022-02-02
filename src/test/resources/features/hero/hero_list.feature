@init
Feature: Hero List API

  Scenario: Get heroes list
    Given I have a valid token
    When I call get heroes list API
    Then should return list data
    And list result should return status code 200

  Scenario: Validate schema
    Given I have a valid token
    When I call get heroes list API
    And I get API schema
    Then schema should be valid

  Scenario: Hero List API should not be called successfully without header
    Given I call get heroes list API without header
    Then should return message error
    And status code should be 401

  Scenario: Hero List API should not be called successfully with invalid header
    Given I call get heroes list API with invalid header
    Then should return message error
    And status code should be 401
