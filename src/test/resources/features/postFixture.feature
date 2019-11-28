Feature: Post a new fixture by Using the model guide in `apiDocs.html`

  Scenario: Post the new fixture
    Given I have postRequestBody
    When I post the post_end_point
    Then I should see new fixture created
    Then I perform DELETE operation for that new fixture

  Scenario: Assert, within the `teams` array, that the first object has a `teamId` of 'HOME'
    Given I have postRequestBody
    When I post the post_end_point
    Then I should see new fixture created
    And Assert the first object has a teamId of HOME
    Then I perform DELETE operation for that new fixture

