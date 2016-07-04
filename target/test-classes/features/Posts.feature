Feature: Posts

  @posts @smoke
  Scenario Outline: I create a post
    Given I want to create a post
    When I create a post
    And I add "<type>" content
    Then a new "<type>" post is created
      Examples:
        | type		|
        | Text		|
        | Media		|
        | Link		|
        | Location	|

  @posts
  Scenario: I create a post tagged with an interest
    Given I want to create a post
    When I create a post tagged with an interest
    Then a new post is created
    And I see the post on my vVoosh Timeline
    And I see the post on my Profile Timeline
    And I see the post on my vVoosh feed
    And I see the post on the Interest Community page

  @posts
  Scenario: I open the details view of a post
    Given I want to create a post
    When I create a post
    And I open the post
    Then I see the post details page

  @posts
  Scenario Outline: I add content to a post
    Given I create a post with "<type1>" content
    When I add "<type2>" content
    Then the post will have both "<type1>" and "<type2>" content
      Examples:
      | type1		| type2		|
      | Text		| Location	|
      | Media		| Link		|
      | Link		| Location	|
      | Location	| Text		|

  @posts
  Scenario Outline: I remove content from a post
    Given I create a post with "<type1>" and "<type2>" content
    When I remove "<type2>" content
    Then the post will have "<type1>" and not "<type2>"
      Examples:
      | type1		| type2		|
      | Text		| Location	|
      | Media       | Link      |
      | Link        | Location  |
      | Location    | Text      |
