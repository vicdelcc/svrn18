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
Feature: Communication
  In order to chat with other people I should be able to send and receive messages

  @tag1
  Scenario: Sending message
    Given a connection to the server is open
    And I am currently logged in with my credentials "profi_chatter"
    And I am currently in a chat room "Dog_Lovers"
    When I type a message "Hello world"
    And I click on the button send or use the enter key of the keyboard
    Then the message should be sent to the server
    And server should send message to every client connected to the chat room "Dog_Lovers"
    And the message should appear in the chat room with the nickname "profi_chatter"


  @tag2
  Scenario: Receiving message
    Given a connection to the server is open
    And I am currently logged in with my credentials "profi_chatter"
    And I am currently in a chat room "Dog_Lovers"
    When another member types a message "Hello world"
    And clicks on the button send or use the enter key of the keyboard
    Then the message should be sent to the server
    And server should send message to every client connected to the chat room "Dog_Lovers"
    And the message should appear in the chat room with the nickname "Peter"
