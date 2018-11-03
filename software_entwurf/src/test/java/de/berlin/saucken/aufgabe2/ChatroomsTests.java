package de.berlin.saucken.aufgabe2;

import de.berlin.saucken.aufgabe2.model.*;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class ChatroomsTests {

    @Test
    public void checkEnterChatroom() throws UserDBException, ChatSeverException {
        // Start chat server with default chatrooms
        ChatServer.start();
        // Generate fake user
        final User user = TestUtil.generateValidFakeUser();
        // Add user to DB without validation
        UserDB.getUsers().add(user);
        // Log user
        ChatServer.logUser(user);
        // Add user to chatroom dog lovers
        ChatServer.addUserToChatroom(user, ChatServer.getChatroom("Dog lovers"));
        // Check if user is in the chat room
        assertTrue(ChatServer.getChatroom("Dog lovers").isMember(user));
    }

    @Test
    public void checkEnterChatroomFailed() throws UserDBException, ChatSeverException {
        // Start chat server with default chatrooms
        ChatServer.start();
        // Generate fake user
        final User user = TestUtil.generateValidFakeUser();
        // Add user to DB without validation
        UserDB.getUsers().add(user);
        // Log user
        ChatServer.logUser(user);
        // Add user to chatroom dog lovers
        try {
            ChatServer.addUserToChatroom(user, ChatServer.getChatroom("Bird lovers"));
        } catch (ChatSeverException e) {
            assertEquals("Chat room not found", e.getMessage());
        }
    }

    @Test
    public void checkLeaveChatroom() throws UserDBException, ChatSeverException {
        // Start chat server with default chatrooms
        ChatServer.start();
        // Generate fake user
        final User user = TestUtil.generateValidFakeUser();
        // Add user to DB without validation
        UserDB.getUsers().add(user);
        // Log user
        ChatServer.logUser(user);
        // Add user to chatroom dog lovers
        ChatServer.addUserToChatroom(user, ChatServer.getChatroom("Dog lovers"));
        // Leave chat room
        ChatServer.removeUserFromChatrom(user, ChatServer.getChatroom("Dog lovers"));
        // Check if user is not in this chat room anymore
        assertFalse(ChatServer.getChatroom("Dog lovers").isMember(user));

    }


    @Test
    public void checkCreationChatroom() throws ChatSeverException {
        // Start chat server with default chatrooms
        ChatServer.start();
        // Create new chat room
        Chatroom chatroomNew = new Chatroom("Bird lovers", "For people who love birds");
        ChatServer.createChatroom(chatroomNew);
        // Check if chat room is active
        assertTrue(chatroomNew.isActive());
    }

    @Test
    public void checkCreationChatroomFailed() {
        // Start chat server with default chatrooms
        ChatServer.start();
        // Create new chat room
        Chatroom chatroomNew = new Chatroom("Dog lovers", "For more people who love dogs");
        try {
            ChatServer.createChatroom(chatroomNew);
        } catch (ChatSeverException e) {
            assertEquals("Chat room with that name already exists", e.getMessage());
        }
        // Check if chat room is active
        assertFalse(chatroomNew.isActive());
    }



}
