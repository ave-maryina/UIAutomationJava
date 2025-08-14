Feature: Testing Authorization page

  Scenario Outline: Testing invalid email data
    When Opening Authorization page
    And Set invalid Email <email>
    Then Check that error message is Invalid email address
    Examples:
      | email              |
      | nane_surname.gmail |
      | nane_surname@      |
      | @gmail.com         |
      | 12@gmail.com       |