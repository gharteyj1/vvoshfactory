Feature: Login

  @login
  Scenario: I login
    Given I am not logged in
    When I log in
    Then I see the vVoosh Timeline

  @login
  Scenario: I fail to login
    Given I am not logged in
    When I log in with invalid credentials
    Then I see a login error

  @login
  Scenario: I logout
    Given I am logged in
    When I log out
    Then I see the vVoosh homepage

  @login
  Scenario: I login with Facebook
    Given I am not logged in
    When I log in with my Facebook account
    Then I see the vVoosh Timeline

  @login
  Scenario: I login with Gmail
    Given I am not logged in
    When I log in with my Chrome account
    Then I see the vVoosh Timeline