Feature: Adding Products to the Cart And Verify Cart

  Background:
    Given User is on the login page with credentials
    When User enters username and password
    Then User lands on the product page

  @addToCart @smoke @loginData @TC01
  Scenario: Add a product to the cart And Verify Cart
    When User selects a product and adds it to the cart
    Then User should see the product in the cart
    And User validates the product name
    And User validates the product price

  @addToCart @loginData @TC01
  Scenario: Add multiple products to the cart And Verify Cart
    When User selects multiple products and adds them to the cart
    Then User should see all selected products in the cart
    And User validates each product name
    And User validates each product price

  @addToCart @loginData @TC01 @smoke
  Scenario: Remove a product from the cart And Verify Cart
    Given User selects a product and adds it to the cart
    When User removes the product from the cart
    Then User should not see the removed product in the cart