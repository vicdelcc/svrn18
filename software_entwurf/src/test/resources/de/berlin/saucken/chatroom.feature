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
Feature: Chat rooms
  In order to chat with other people I need to enter an existing chat room or create one. If I don't want to chat
  anymore I can leave the chat room

  @tag1
  Scenario: Enter an existing chat room
    Given a connection to the server is open
    And I am logged in with my nickname "profi_chatter"
    And a list of available chat rooms is shown
    When I select a chat room by clicking on its name as "Dog_Lovers"
    And I click on the button enter
    Then I should see a message saying "You entered the chat room"
    And I should be in the selected chat room "Dog_Lovers" with my nickname as "profi_chatter"
    And a message should be sent to the rest of the chat room "Dog_Lovers" members saying "profi_chatter" entered the room

  @tag2
  Scenario: Create a new chat room
    Given a connection to the server is open
    And I am logged in with my nickname "profi_chatter"
    And I click on the button create new chat room
    And I enter a chat room name as "new_chat_room"
    And I click on the button create
    Then I should see a message saying "Chat room successfully created"
    And chat room should be listed in the chat server and shown to all users logged in with credentials


  @tag3
  Scenario: Leave a chat room
    Given a connection to the server is open
    And I am currently in a chat room "Dog_Lovers"
    When I click on the button leave
    And I confirm I want to leave
    Then I should see a message saying "You have left the chat room"
    And a message should be shown to the rest of the chat room "Dog_Lovers" members saying username left
