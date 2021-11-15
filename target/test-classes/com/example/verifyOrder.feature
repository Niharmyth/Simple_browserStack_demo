Feature: whatever

  @Nihar
  Scenario: E2E BrowserStack Scenario
    Given I navigate to Website
    Then I SignIn with username as "demouser" and password as "testingisfun99"
    And I add "iPhone 11" to the cart
    And I click on Buy button
    And I provide shipping details "Nihar", "J", "31 Mesik", "NSW" and "2762"
    And I verify my Order
    And I click on Continue Shopping button
    And I click on "Orders" link
    Then I should see orders in the list