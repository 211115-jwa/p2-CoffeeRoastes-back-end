
Feature: sign in in as a user

  Scenario Outline: sing in to view cart and review
    Given the user is on the CofferoastersApp home page
    And the user clicks the Log In link
    When the user enters "<username>" and "<password>" to sign in
    And some other action
    And the user clicks the login button
    Then the navbar says "<username>"

    

  Scenario Outline: sign in with incorrect passwords
    Given the user is on the CofferoastersApp home page
    And the user clicks the Sign In link
    When the user enters "<username>" and "<password>" to sign in
    And the user clicks the login button
    Then the page says Incorrect Credentials

   