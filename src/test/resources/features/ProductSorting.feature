Feature: Products Sorting Functionality

  Background:
    Given User is on the login page with credentials
    When User enters username and password
    Then User lands on the product page

  @sorting @smoke @loginData @TC01
  Scenario: Sort Products By Price Low to High And Validate Order
    When User selects the sort option "Price: Low to High"
    Then Validate whether the products are sorted by price in ascending order

  @sorting @loginData @TC01
  Scenario: Sort Products By Price High to Low And Validate Order
    When User selects the sort option "Price: High to Low"
    Then Validate whether the products are sorted by price in descending order

  @sorting @smoke @loginData @TC01
  Scenario: Sort Products By Name A to Z And Validate Order
    When User selects the sort option "Name: A to Z"
    Then Validate whether the products are sorted by name in alphabetical order

  @sorting @loginData @TC01
  Scenario: Sort Products By Name Z to A And Validate Order
    When User selects the sort option "Name: Z to A"
    Then Validate whether the products are sorted by name in reverse alphabetical order