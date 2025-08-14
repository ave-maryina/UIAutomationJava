Feature: Testing Authorization page

  Background:
    Given Set up driver

  Scenario Outline: Testing invalid email data
    When Opening Authorization page
    And Set invalid Email <email>
    Then Check that error message is Invalid email address
    Then Quit driver
    Examples:
      | email              |
      | nane_surname.gmail |
      | nane_surname@      |
      | @gmail.com         |
      | 12@gmail.com       |



