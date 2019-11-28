Feature: Create and delete a new fixture

  Scenario: Creating and deleting a new fixture
    Given I ensure to perform POST new fixture
    And I perform GET operation for new fixture
    Then I perform DELETE operation for that new fixture
    Then I Assert that the new fixture no longer exists