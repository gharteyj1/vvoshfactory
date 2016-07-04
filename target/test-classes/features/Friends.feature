Feature: Friends

  @friends @smoke
  Scenario: I send a friend request that is accepted
    Given I am not friends with User A
    And I send a friend request to User A
    When the friend request is accepted
    Then User A and I become friends

  @friends
  Scenario: I send a friend request that is rejected
    Given I am not friends with User A
    And I send a friend request to User A
    When the friend request is rejected
    Then User A and I do not become friends

  @friends @smoke
  Scenario: I un-friend someone
    Given I am not friends with User A
    And I send a friend request to User A
    And the friend request is accepted
    When I un-friend User A
    Then User A and I are no longer friends

  @friends
  Scenario: I send a friend request that is rejected via the notification tab
    Given I am not friends with User A
    And I send a friend request to User A
    When the friend request is rejected via the notification tab
    Then User A and I do not become friends

  @friends
  Scenario: I send a friend request that is accepted via the notification tab
    Given I am not friends with User A
    And I send a friend request to User A
    When the friend request is accepted via the notification tab
    Then User A and I become friends