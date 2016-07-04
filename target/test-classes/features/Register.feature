Feature: Register

  @register
  Scenario: I register a new account
    Given I receive an invite
    And I begin registration process
    And I register
    When Love and Try several interests
    Then registration is complete

  @register
  Scenario: I register a user and then authorise the email address
    Given I receive an invite
    And I begin registration process
    And I register
    And Love and Try several interests
    When I open my email account
    Then my email address is confirmed

  @register
  Scenario: I register a new vVoosh account
    Given I register my email address
    And I begin registration process
    And I register
