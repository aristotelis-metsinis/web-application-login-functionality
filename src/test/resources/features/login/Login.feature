@feature:login @testing:regression @testing:acceptance
Feature: User Login
  As a user
  I want to log into the application
  So that I can access my account

  @feature:login @id:login-001 @scenario:successful_login @testing:acceptance @type:positive_test
  Scenario Outline: Successful login
    Given I am on the Login page
    When I login with the following credentials:
      | Field    | Value      |
      | Username | <username> |
      | Password | <password> |
    Then I should be redirected to the landing page
    Examples:
      | username  | password    |
      | student   | Password123 |

  @feature:login @id:login-002 @scenario:invalid_username @testing:regression @type:negative_test
  Scenario: Invalid username
    Given I am on the Login page
    When I login with the following credentials:
      | Field    | Value         |
      | Username | incorrectUser |
      | Password | Password123   |
    Then I should see the error message "Your username is invalid!"

  @feature:login @id:login-003 @scenario:invalid_password @testing:regression @type:negative_test
  Scenario: Invalid password
    Given I am on the Login page
    When I login with the following credentials:
      | Field    | Value             |
      | Username | student           |
      | Password | incorrectPassword |
    Then I should see the error message "Your password is invalid!"

  @feature:login @id:login-004 @scenario:empty_fields @testing:regression @type:negative_test
  Scenario: Both fields empty
    Given I am on the Login page
    When I login with the following credentials:
      | Field    | Value |
      | Username |       |
      | Password |       |
    Then I should see the error message "Your username is invalid!"

  @feature:login @id:login-005 @scenario:username_empty @testing:regression @type:negative_test
  Scenario: Username empty
    Given I am on the Login page
    When I login with the following credentials:
      | Field    | Value       |
      | Username |             |
      | Password | Password123 |
    Then I should see the error message "Your username is invalid!"

  @feature:login @id:login-006 @scenario:password_empty @testing:regression @type:negative_test
  Scenario: Password empty
    Given I am on the Login page
    When I login with the following credentials:
      | Field    | Value   |
      | Username | student |
      | Password |         |
    Then I should see the error message "Your password is invalid!"

  @feature:login @id:login-007 @scenario:sql_injection @testing:security @type:negative_test
  Scenario: SQL Injection attempt
    Given I am on the Login page
    When I login with the following credentials:
      | Field    | Value        |
      | Username | ' OR 1=1 --  |
      | Password | Password123  |
    Then I should see the error message "Your username is invalid!"

  @feature:login @id:login-008 @scenario:xss_attempt @testing:security @type:negative_test
  Scenario: XSS attempt
    Given I am on the Login page
    When I login with the following credentials:
      | Field    | Value                                      |
      | Username | <script>/*XSS-TOKEN: TOKEN_123 */</script> |
      | Password | Password123                                |
    Then I should see the error message "Your username is invalid!"

  @feature:login @id:login-009 @scenario:long_input @testing:exploratory @type:negative_test
  Scenario: Long input in fields
    Given I am on the Login page
    When I login with the following credentials:
      | Field    | Value                                                                                                                                                                                                                                                            |
      | Username | aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa |
      | Password | aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa |
    Then I should see the error message "Your username is invalid!"