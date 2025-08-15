Feature: Testing Registration page

  Scenario Outline: Testing of positive filling Registration page where First name consists of the minimum allowed length(2 characters)
    Given Opening Registration page
    When Set First name  <firstName>
    And Set Last name  lastName
    And Set Date of birth  09/12/2000
    And Close pop up calendar
    And Set valid Email
    And Set Password 12345678
    And Set Confirm password 12345678
    And Click Submit button
    Then Check Current url doesn't contain /login
    Examples:
      | firstName |
      | Na        |


  Scenario Outline: Testing that an error message is displayed if the 'Password' and 'Confirm password' fields do not match
    Given Opening Registration page
    And Set Password <password>
    And Set Confirm password <confirm password>
    Then Check that error message is Passwords must match
    Examples:
      | password | confirm password |
      | 12345678 | 12345679         |
      | 123p5678 | 02345679         |
      | 1234567h | 123n5679         |