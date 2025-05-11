Feature: User Login Feature

  Background:
    Given User is on the login page with credentials
    When User enters username and password

  @loginData @TC01 @smoke
  Scenario: User Login With Valid Credentials
    Then Validate whether the user is logged in successfully

  @loginData @TC02 @smoke
  Scenario: User Login With Invalid Credentials
    Then Validate that an invalid login error is displayed

  @loginData @TC03 @smoke
  Scenario: User Login With Locked-out User Credentials
    Then Validate that a locked-out error is displayed

  @loginData @TC04
  Scenario: User Login With Empty Username
    Then Validate that the username required error is displayed

  @loginData @TC05
  Scenario: User Login With Empty Password
    Then Validate that the password required error is displayed
