Feature: Register

  @onboarding
  Scenario: I complete onboarding
    Given I begin the onboarding process
    When I confirm I understand the love/try feature
    And I add interests
    And I connect with friends
    And I add my personal details
    And I choose to proceed to the feed
    Then I see the myvVoosh feed