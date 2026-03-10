Feature: Validate the working of Register Account functionality

  Background:
  Given I am on opencart homepage

    @sanity @regression
    Scenario: TC_RF_001 (Validate Registering an Account by providing only the Mandatory fields)
      Given I Clicked on My Account Drop down
      Given I Clicked on Register option
      Given I am on Register page
      Given I Entered new Account Details into the Mandatory Fields
      When I Clicked on Continue button
      When I Clicked on Continue button that is displayed in the Account Success page
      Then I should be taken to Account page





