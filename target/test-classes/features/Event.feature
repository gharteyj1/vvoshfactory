Feature: Event

  @events
  Scenario Outline: Create a Complete Event
    Given I want to create an event
    When I create a complete "<type>" event
    And I add a "<link>" to event
    And Event is Submitted
    Then I should see the Event Details page
    Examples:
      | type | link |
      |Public| http://www.bbc.co.uk |
      |Private| https://beta.vvoosh.com |

  @WIP
  Scenario Outline: Private Event cannot be seen by friends
    Given I want to create an event
    When I create a complete "<type>" event
    And Event is Submitted
    Then Uninvited Users cannot see the Private Event
    Examples:
      | type |
      |Private|

  @events
  Scenario: View an event in My Events
    Given I want to create an event
    When I create a public event
    When I view My Events
    Then I should see the event

  @events
  Scenario: Delete and Event
     Given I want to create an event
     When I create a public event
     And I view My Events
     Then I remove an event
     And my event is removed successfully


