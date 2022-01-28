@tag
Feature: sign in in as a user

  @tag1
  Scenario Outline: sing in to view cart and review
    Given the user is on the CofferoastersApp home page
    And the user clicks the Log In link
    When the user enters "<username>" and "<password>" to sign in
    And some other action
    And the user clicks the login button
    Then the navbar says "<username>"

    Examples: 
      | username  | password |
      | Katthewm  | pass     |
      | dkinzett1 | pass     |

  @tag2
  Scenario Outline: sign in with incorrect passwords
    Given the user is on the CofferoastersApp home page
    And the user clicks the Sign In link
    When the user enters "<username>" and "<password>" to sign in
    And the user clicks the login button
    Then the page says Incorrect Credentials

    Examples: 
      | username  | password |
      | rdowden4  | p4ss     |
      | padriano7 | Pass22   |
      | amulrana  | pass12   |
