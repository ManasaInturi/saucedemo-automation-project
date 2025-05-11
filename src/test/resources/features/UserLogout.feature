Feature: User Logout

Background:
    Given User is on the login page with credentials
    When User enters username and password
    Then User lands on the product page

@logout @smoke @loginData @TC01
Scenario: User Logout
    When User clicks on the logout button
    Then Validate whether the user is logged out successfully
    And Validate whether the user is redirected to the login page
    And Validate whether the login page is displayed correctly