package de.berlin.saucken.aufgabe1;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

import java.util.*;

import static org.junit.Assert.assertEquals;

public class ChatRoomsSteps {

    private Map<String, List<String>> chatrooms = new HashMap<String, List<String>>()  {{
        put("Dog_Lovers", new ArrayList<>(Arrays.asList("Peter", "John")));
        put("Cat_Lovers", new ArrayList<>(Arrays.asList("Mike", "Susanne")));
    }};

    private boolean isLogged = true;

    private boolean buttonEnterClicked;
    private boolean buttonNewChatClicked;
    private String newChatroomName = "new_chat_room";
    private String nickname = "profi_chatter";
    private boolean buttonCreateClicked;
    private boolean buttonLeaveClicked;
    private boolean buttonConfirmClicked;


    @Given("^I am logged in with my nickname \"([^\"]*)\"$")
    public void i_am_logged_in_with_my_nickname(String arg1) throws Exception {
       this.nickname = arg1;
    }

    @Given("^a list of available chat rooms is shown$")
    public void a_list_of_available_chat_rooms_is_shown() throws Exception {
        System.out.println(String.join(",",chatrooms.keySet()));
    }

    @When("^I select a chat room by clicking on its name as \"([^\"]*)\"$")
    public void i_select_a_chat_room_by_clicking_on_its_name_as(String arg1) throws Exception {
        assertEquals(chatrooms.containsKey(arg1), true);
    }

    @When("^I click on the button enter$")
    public void i_click_on_the_button_enter() throws Exception {
        this.buttonEnterClicked = true;
    }

    @Then("^I should be in the selected chat room \"([^\"]*)\" with my nickname as \"([^\"]*)\"$")
    public void i_should_be_in_the_selected_chat_room_with_my_nickname_as(String arg1, String arg2) throws Exception {
        chatrooms.get(arg1).add(arg2);
        assertEquals(chatrooms.get(arg1).contains(arg2), true);
    }

    @Then("^a message should be sent to the rest of the chat room \"([^\"]*)\" members saying \"([^\"]*)\" entered the room$")
    public void a_message_should_be_sent_to_the_rest_of_the_chat_room_members_saying_entered_the_room(String arg1, String arg2) throws Exception {
        // Erst mal so viel Prints wie members
        for(String member: chatrooms.get(arg1)) {
            System.out.println(arg2 + " entered the room");
        }
    }

    @Given("^I click on the button create new chat room$")
    public void i_click_on_the_button_create_new_chat_room() throws Exception {
            this.buttonNewChatClicked = true;
    }

    @Given("^I enter a chat room name as \"([^\"]*)\"$")
    public void i_enter_a_chat_room_name_as(String arg1) throws Exception {
            this.newChatroomName = arg1;
    }

    @Given("^I click on the button create$")
    public void i_click_on_the_button_create() throws Exception {
            this.buttonCreateClicked = true;
    }

    @Then("^chat room should be listed in the chat server and shown to all users logged in with credentials$")
    public void chat_room_should_be_listed_in_the_chat_server_and_shown_to_all_users_logged_in_with_credentials() throws Exception {
        chatrooms.put(this.newChatroomName, Arrays.asList(this.nickname));

        // Hier müsste man die Liste von eingeloggten Nutzer iterieren
        System.out.println(this.newChatroomName);
    }

    @Given("^I am currently in a chat room \"([^\"]*)\"$")
    public void i_am_currently_in_a_chat_room(String arg1) throws Exception {
            chatrooms.get(arg1).add(this.nickname);
            assertEquals(chatrooms.get(arg1).contains(this.nickname), true);
    }

    @When("^I click on the button leave$")
    public void i_click_on_the_button_leave() throws Exception {
            this.buttonLeaveClicked = true;
    }

    @When("^I confirm I want to leave$")
    public void i_confirm_I_want_to_leave() throws Exception {
            this.buttonConfirmClicked = true;
            for(String chatroom : chatrooms.keySet() ) {
                if(chatrooms.get(chatroom).contains(this.nickname)) {
                    chatrooms.get(chatroom).remove(this.nickname);
                }
            }
    }

    @Then("^a message should be shown to the rest of the chat room \"([^\"]*)\" members saying username left$")
    public void a_message_should_be_shown_to_the_rest_of_the_chat_room_members_saying_username_left(String arg1) throws Exception {
        for(String members : chatrooms.get(arg1)) {
            System.out.println(this.nickname + " left the chat room");
        }
    }
}
