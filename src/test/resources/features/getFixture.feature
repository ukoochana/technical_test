Feature: Retrieve all the fixtures.

  Scenario: Assert that there are 3 fixtures within the returned object
    Given I perform get operation for fixtures
    Then Assert that there are 3 fixtures within the returned object

  Scenario: Assert that each of the 3 fixtures has a fixtureId value
    Given I perform get operation for fixtures
    Then Assert that each of the three fixtures has a fixtureId value