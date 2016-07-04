Feature: Login

  @login @smoke
  Scenario: I login
    Given I am not logged in
    When I log in
    Then I see the vVoosh Timeline

  @login @smoke
  Scenario: I fail to login
    Given I am not logged in
    When I log in with invalid credentials
    Then I see a login error

  @login @smoke
  Scenario: I logout
    Given I am logged in
    When I log out
    Then I see the vVoosh homepage
