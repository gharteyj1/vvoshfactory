Feature: Event

  @events
  Scenario: I create an event
    Given I want to create an event
    When I create a "Public" event
    And I save the event
    Then I should see the Event Details page

  @events
  Scenario: I create an event with a Link
    Given I want to create an event
    When I create a "Public" event
    And I add the link "https://www.google.com"
    And I save the event
    Then I should see the Event Details page
    And I see the link

  @events
  Scenario: I create an event with a cover image
    Given I want to create an event
    When I create a "Public" event
    And I add a cover image
    And I save the event
    Then I should see the Event Details page
    And I see the cover image

  @events
  Scenario: I create an event with an interest
    Given I want to create an event
    When I create a "Public" event
    And I add the interest "film"
    And I save the event
    Then I should see the Event Details page
    And the interest "film" has been tagged

  @events
  Scenario: I create an event with a specific datetime
    Given I want to create an event
    When I create a "Public" event
    And I set the date
    And I save the event
    Then I should see the Event Details page
    And I see the date I specified

  @events
  Scenario: I create an event with a specific location
    Given I want to create an event
    When I create a "Public" event
    And I add the location "London, UK"
    And I save the event
    Then I should see the Event Details page
    And the event location is "London, UK"

  @events
  Scenario: I create an all day event
    Given I want to create an event
    When I create a "Public" event
    And I set the event to occur all day
    And I save the event
    Then I should see the Event Details page
    And the event occurs all day

  @events
  Scenario: I invite a friend to my event
    Given I want to create an event
    When I create a "Private" event
    And I invite a friend
    And I save the event
    Then I should see the Event Details page
    And the friend is invited

  @WIP
  Scenario: I create a public event
    Given I want to create an event
    When I create a "Public" event
    And I save the event
    Then I should see the Event Details page
    And I see the event in My Events
    And I see the event in myvVoosh Timeline
    And I see the event in my Profile Events page
    And my friends see the event in their myvVoosh Timeline
    And my friends see the event in my Profile Timeline
    And strangers see the event in my Profile Timeline
    And strangers see the event in my Profile Events page

  @events
  Scenario: I create a private event
    Given I want to create an event
    When I create a "Private" event
    And I invite a friend
    And I save the event
    Then I should see the Event Details page
    And I see the event in My Events
    And I see the event in myvVoosh Timeline
    And I see the event in my Profile Events page
    And invitees see the event in their myvVoosh Timeline
    And invitees see the event in their My Events
    And invitees see the event in my Profile Timeline

  @events
  Scenario: Delete event
     Given I want to create an event
     And I create a "Public" event
     And I save the event
     When I remove the event
     Then the event does not appear in My Events