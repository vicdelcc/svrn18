package de.berlin.saucken.aufgabe1;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

import java.util.*;

public class CommunicationSteps {


    private Map<String, List<String>> serverRooms = new HashMap<String, List<String>>()  {{
        put("Dog_Lovers", new ArrayList<>(Arrays.asList("Peter", "John")));
        put("Cat_Lovers", new ArrayList<>(Arrays.asList("Mike", "Susanne")));
    }};

    private Map<String, List<String>> serverMessages = new HashMap<String, List<String>>()  {{
        put("Dog_Lovers", new ArrayList<>(Arrays.asList("Chat open")));
        put("Cat_Lovers", new ArrayList<>(Arrays.asList("Chat open")));
    }};

    private String message;
    private boolean buttonSendClicked;
    private boolean keySendPressed;

    private String chatroomName = "Dog_Lovers";

    private String nickname;

    @Given("^I am currently logged in with my credentials \"([^\"]*)\"$")
    public void i_am_currently_logged_in_with_my_credentials(String arg1) throws Exception {
        this.nickname = arg1;
    }

    @When("^I type a message \"([^\"]*)\"$")
    public void i_type_a_message(String arg1) throws Exception {
        this.message = arg1;
    }

    @When("^I click on the button send or use the enter key of the keyboard$")
    public void i_click_on_the_button_send_or_use_the_enter_key_of_the_keyboard() throws Exception {
        this.buttonSendClicked = true;
        this.keySendPressed = true;
    }

    @Then("^the message should be sent to the server$")
    public void the_message_should_be_sent_to_the_server() throws Exception {

        // Message to server, for now add to list
        serverMessages.get(chatroomName).add(this.message);
    }

    @Then("^server should send message to every client connected to the chat room \"([^\"]*)\"$")
    public void server_should_send_message_to_every_client_connected_to_the_chat_room(String arg1) throws Exception {

        // For now print, last message added to list
        for(String members : serverRooms.get(arg1)) {
            System.out.println(serverMessages.get(arg1).get(serverMessages.get(arg1).size()-1));
        }
    }

    @When("^another member types a message \"([^\"]*)\"$")
    public void another_member_types_a_message(String arg1) throws Exception {
            this.message = arg1;
    }

    @When("^clicks on the button send or use the enter key of the keyboard$")
    public void clicks_on_the_button_send_or_use_the_enter_key_of_the_keyboard() throws Exception {
        this.buttonSendClicked = true;
        this.keySendPressed = true;
    }


    @Then("^the message should appear in the chat room with the nickname \"([^\"]*)\"$")
    public void the_message_should_appear_in_the_chat_room_with_the_nickname(String arg1) throws Exception {
        System.out.println(nickname + ": " + this.message);
    }

}
