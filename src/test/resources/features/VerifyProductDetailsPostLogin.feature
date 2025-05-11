Feature: Verify Product Details on Landing Page

  Background:
    Given User is on the login page with credentials
    When User enters username and password
    Then User lands on the product page

  @smoke @loginData @verifyProducts @TC01
  Scenario: Validate all product names and prices are displayed correctly
    When User fetches all product names and prices
    Then User should see all product names displayed
    And User should see all product prices displayed