Feature: Validate The Product Details

  Background:
    Given User is on the login page with credentials
    When User enters username and password
    Then User lands on the product page

  @productDetails @smoke @loginData @TC01
  Scenario Outline: Validate the product details
    When User selects a product "<product>" and navigates to the details page
    Then User should see the product details
    And User validates the product "<product>" name
    And User validates the product "<product>" price
    And User validates the product "<product>" description

    Examples:
        | product                              |
        | Sauce Labs Backpack                  |
        | Sauce Labs Bike Light                |
        | Sauce Labs Bolt T-Shirt              |
        | Sauce Labs Fleece Jacket             |
        | Sauce Labs Onesie                    |
        | Test.allTheThings() T-Shirt (Red)    |