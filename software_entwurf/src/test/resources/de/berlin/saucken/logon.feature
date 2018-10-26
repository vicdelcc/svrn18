#Author: your.email@your.domain.com
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
Feature: Logon with an existing account
  In order to use the chat server, I want to submit my credentials and establish a session


  @tag1
  Scenario Outline: Request new password
    Given a connection to the server is open
    When I click on the button reset password
    And I enter <quality1> <credentials1> as <content1>
    And I click on the link reset password
    And I choose a <quality2> password as <newpassword>
    And I retype <quality3> password as <retypedpassword>
    Then I should see a message saying <message>
    Examples:
      | quality1 | credentials1 | content1             | quality2 | newpassword   | quality3 | retypedpassword | message                                  |
      | good     | email        | "test@gmail.com"     | good     | "abc123"      | good     | "abc123"        | "Password successfully changed"          |
      | bad      | email        | "notfound@gmail.com" | bad      | "badpassword" | bad      | "badpassword"   | "Email not found"                        |
      | good     | email        | "test@gmail.com"     | good     | "abc123"      | bad      | "badpassword"   | "Passwords don't match"                  |
      | good     | email        | "test@gmail.com"     | bad      | "badpassword" | bad      | "badpassword"   | "Password doesn't meet the requirements" |


  @tag2
  Scenario Outline: Submit credentials
    Given a connection to the server is open
    When I enter <quality1> <credentials1> as <content1>
    And I enter <quality2> <credentials2> as <content2>
    Then I should see a message saying <message>

    Examples:
      | quality1 | credentials1 | content1             | quality2 | credentials2 | content2      | message                      |
      | good     | email        | "test@gmail.com"     | good     | password     | "abc123"      | "Welcome to the chat server" |
      | good     | email        | "test@gmail.com"     | bad      | password     | "badpassword" | "Password wrong"             |
      | bad      | email        | "notfound@gmail.com" | bad      | password     | "badpassword" | "Email not found"            |
      | bad      | email        | "notfound@gmail.com" | good     | password     | "abc123"      | "Email not found"            |
