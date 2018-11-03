package de.berlin.saucken.aufgabe2;

import de.berlin.saucken.aufgabe2.model.*;
import org.junit.Test;

import static org.junit.Assert.*;

public class LogonTests {

    /**
     * Testet einen erfolgreichen Login
     * @throws UserDBException
     */
    @Test
    public void checkLogin() throws UserDBException, ChatSeverException {

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
        // Check if user is logged in
        assertTrue(ChatServer.getUsersConnected().contains(user));
    }

    @Test
    public void checkLoginFailedServerNotRunning() {
        // Generate fake user
        final User user = TestUtil.generateValidFakeUser();
        // Add user to DB without validation
        UserDB.getUsers().add(user);
        // Log user with a null-Chatserver
        try {
            ChatServer.logUser(user);
        } catch (ChatSeverException | UserDBException e) {
            assertEquals("Chat server is not running", e.getMessage());
        }
    }

    /**
     * Testet, ob ein Login mit einer nicht registrierten Email fehlschlägt
     */
    @Test
    public void checkLoginFailedEmailNotFound() {
        // Start chat server
        ChatServer.start();
        // Check if chat server is running
        assertTrue(ChatServer.isRunning());
        // Generate fake user
        final User user = TestUtil.generateValidFakeUser();
        // User not added to DB since it shouldn't be present

        // Check if user in DB
        try {
            ChatServer.logUser(user);
            assertTrue(true);
        } catch (ChatSeverException | UserDBException e) {
            assertEquals("Email is not registered", e.getMessage());
        }
    }

    /**
     * Testet ob ein Login mit einem falschen Passwort fehlschlägt
     * @throws UserDBException
     */
    @Test
    public void checkLoginFailedPasswordWrong() {
        // Start chat server
        ChatServer.start();
        // Check if chat server is running
        assertTrue(ChatServer.isRunning());
        // Generate fake user
        User userDB = TestUtil.generateValidFakeUser();
        // Add user to DB without validation
        UserDB.getUsers().add(userDB);
        // "Same user" but another password
        User userToCheck = TestUtil.generateValidFakeUser();
        userToCheck.setEmail(userDB.getEmail());
        userToCheck.setNickname(userDB.getNickname());
        userToCheck.setPassword("wrongpassword");
        // Check answer from server
        try {
            ChatServer.logUser(userToCheck);
        } catch (ChatSeverException | UserDBException e) {
            assertEquals("Password is wrong", e.getMessage());
        }
    }


}
