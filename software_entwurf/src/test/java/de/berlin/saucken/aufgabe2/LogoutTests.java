package de.berlin.saucken.aufgabe2;

import de.berlin.saucken.aufgabe2.model.*;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class LogoutTests {

    @Test
    public void checkLogout() throws UserDBException, ChatSeverException {

        // Start chat server
        ChatServer.start();
        // Check if chat server is running
        assertTrue(ChatServer.isRunning());
        // Generate fake user
        final User user = TestUtil.generateValidFakeUser();
        // Add user to DB without validation
        UserDB.getUsers().add(user);
        // Log user
        ChatServer.logUser(user);
        // Logout user
        ChatServer.logoutUser(user);
        // Check if user is logged out
        assertFalse(ChatServer.getUsersConnected().contains(user));


    }

    @Test
    public void checkLogoutFailed() {

        // Start chat server
        ChatServer.start();
        // Check if chat server is running
        assertTrue(ChatServer.isRunning());
        // Generate fake user
        final User user = TestUtil.generateValidFakeUser();
        // Dont log user

        // Logout user
        try {
            ChatServer.logoutUser(user);
        } catch (ChatSeverException e) {
            assertEquals("User is not logged in", e.getMessage());
        }

    }
}
