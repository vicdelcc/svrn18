package de.berlin.saucken.aufgabe2;

import de.berlin.saucken.aufgabe2.model.*;
import org.junit.Test;

import java.time.LocalDateTime;

import static org.junit.Assert.assertTrue;

public class CommunicationTests {

    @Test
    public void checkSendMessage() throws UserDBException, ChatSeverException {
        // Start chat server with default chatrooms
        ChatServer.start();
        // Generate fake user
        final User user = TestUtil.generateValidFakeUser();
        // Add user to DB without validation
        UserDB.getUsers().add(user);
        // Log user
        ChatServer.logUser(user);
        // Add user to chatroom dog lovers
        Chatroom chatroomDogs = ChatServer.getChatroom("Dog lovers");
        ChatServer.addUserToChatroom(user, chatroomDogs);
        // Create new message
        Message message = new Message("Hello world", user, LocalDateTime.now());
        // Send message
        ChatServer.sendMessage(chatroomDogs, message);
        // Check if message in chat room
        assertTrue(chatroomDogs.getMessages().contains(message));
    }


    @Test
    public void checkReceiveMessage() throws UserDBException, ChatSeverException {
        // Start chat server with default chatrooms
        ChatServer.start();
        // Get one chatroom
        Chatroom chatroomDogs = ChatServer.getChatroom("Dog lovers");
        // Generate fake user several fake users, log them and add to same chat room
        for(int i = 0; i<5; i++) {
            final User user = TestUtil.generateValidFakeUser();
            UserDB.getUsers().add(user);
            ChatServer.logUser(user);
            ChatServer.addUserToChatroom(user, chatroomDogs);
        }
        // One of the users send the message
        Message message = new Message("Hello world", chatroomDogs.getMembers().get(0), LocalDateTime.now());
        // Send message
        ChatServer.sendMessage(chatroomDogs, message);
        // Check if message in chat room
        assertTrue(chatroomDogs.getMessages().contains(message));
        // Check if displays of all members have the message
        for(User user : chatroomDogs.getMembers()) {
            assertTrue(user.getMessagesReceived().contains(message));
        }
    }


}
