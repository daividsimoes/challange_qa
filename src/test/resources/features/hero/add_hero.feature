@init
Feature: Add Hero API

  Scenario: Add hero successfully
    Given I have one hero performed
    And I have a valid token
    When I call add hero API
    Then should add hero successfully
    And status code should be 201

  Scenario: Validate schema
    Given I have one hero performed
    And I have a valid token
    When I call add hero API
    And I get API schema
    Then schema should be valid

    Scenario: Validate can not add same hero twice
      Given I already have one hero added
      When I call add hero API
      Then should return message error
      And status code should be 500
