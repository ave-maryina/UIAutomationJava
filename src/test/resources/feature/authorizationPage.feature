Feature: Testing Authorization page

  Scenario Outline: Testing invalid email data
    Given Opening Authorization page
    When Set invalid Email <email>
    Then Check that error message is Invalid email address
    Examples:
      | email              |
      | nane_surname.gmail |
      | nane_surname@      |
      | @gmail.com         |