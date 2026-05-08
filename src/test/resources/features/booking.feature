Feature: Booking API

  Scenario: Create a booking successfully
    When I create a valid booking
    Then the booking response status code should be 201
    And the booking response should contain a booking id
    And the booking response should contain the created booking details

  Scenario: Retrieve a booking by id
    Given I have created a valid booking
    And I am authenticated as an admin user
    When I retrieve the booking by id
    Then the booking response status code should be 200
    And the retrieved booking should match the created booking