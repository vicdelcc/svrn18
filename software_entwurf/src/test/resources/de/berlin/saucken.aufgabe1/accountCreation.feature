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
Feature: Creation of a new account
  In order to use the chat server, I want to create a new account by entering my personal data and choosing a password.
  After that I want to confirm my email address by clicking on the verification link. Finally, I want to submit my
  credentials and establish a sessionIn order to use the chat server, I want to create a new account by entering my
  personal data and choosing a password. After that I want to confirm my email address by clicking on the verification
  link. Finally, I want to submit my credentials and establish a session

  @tag1
  Scenario Outline: Request account creation
    Given a connection to the server is open
    When I click on the button create new account
    And I enter new <quality1> <credentials1> as <content1>
    And I enter new <quality2> <credentials2> as <content2>
    And I choose <quality3> password as <password>
    And I retype <quality4> password as <retypedpassword>
    And I click on the button send creation account form
    And I click on the link confirm account creation
    Then I should see a message saying <message>
    Examples:
      | quality1 | credentials1 | content1                  | quality2 | credentials2 | content2        | quality3 | password      | quality4 | retypedpassword | message                                  |
      | good     | email        | "test_new@gmail.com"      | good     | nickname     | "profi_chatter" | good     | "abc123"      | good     | "abc123"        | "Account successfully created"           |
      | good     | email        | "test_new@gmail.com"      | good     | nickname     | "profi_chatter" | good     | "abc123"      | bad      | "badpassword"   | "Passwords don't match"                  |
      | good     | email        | "test_new@gmail.com"      | good     | nickname     | "profi_chatter" | bad      | "badpassword" | bad      | "badpassword"   | "Password doesn't meet the requirements" |
      | good     | email        | "test_new@gmail.com"      | bad      | nickname     | "already_taken" | bad      | "badpassword" | bad      | "badpassword"   | "Nickname already in use"                |
      | bad      | email        | "already_taken@gmail.com" | bad      | nickname     | "already_taken" | bad      | "badpassword" | bad      | "badpassword"   | "Email already registered"               |
