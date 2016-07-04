Feature: Settings

  @settings @smoke
  Scenario: Upload a profile photo
    Given I want to upload a new profile photo
    When I add a new profile photo
    Then I see the new profile photo in my profile page

  @settings @smoke
  Scenario: Upload a cover photo
    Given I want to upload a new cover photo
    When I add a new cover photo
    Then I see the new cover photo in my profile page