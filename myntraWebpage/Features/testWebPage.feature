#Author: lawanyaravi352@gmail.com
#Keywords Summary :
#Feature: List of scenarios.
#Scenario: Business rule through list of steps with arguments.
#Given: Some precondition step
#When: Some key actions
#Then: To observe outcomes or validation
#And,But: To enumerate more Given,When,Then steps
#Scenario Outline: List of steps for data-driven as an Examples and <placeholder>
#Examples: Container for s table
#Background: List of steps run before each of the scenarios
#""" (Doc Strings)
#| (Data Tables)
#@ (Tags/Labels):To group Scenarios
#<> (placeholder)
#""
## (Comments)
#Sample Feature Definition Template

@tag
Feature: Myntra Functionality Check
  I want to check the functionality of Myntra site

  @TC-001
  Scenario: Check Add to Cart Functionality
    Given User is in homepage 
		And Logo is visible
    Then Cart has no items
    When User searches "red tshirt"
    Then Search results should be displayed
    Then Sort option should be displayed
    When User changes sort option
    Then Cheapest item should be selected
    Then Select item size
    When Item is available
    And User clicks Add to Cart
    Then Go to Cart is displayed
    And Items count should be updated to "1"
    When User clicks Go to Cart
    Then Verify item in cart
    
