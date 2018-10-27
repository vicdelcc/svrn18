package de.berlin.saucken.aufgabe2;

import de.berlin.saucken.aufgabe1.model.User;
import de.berlin.saucken.aufgabe1.model.UserDB;
import de.berlin.saucken.aufgabe1.model.UserDBException;
import org.junit.Test;

import static org.junit.Assert.*;

public class AccountCreationTests {

    /**
     * Testet ob ein User erfolgreich angelegt wird
     */
    @Test
    public void checkCreationUser() throws UserDBException {
        final User user = TestUtil.generateValidFakeUser();
        UserDB.createUser(user, user.getPassword());
        assertTrue(UserDB.getUsers().contains(user));
    }

    /**
     * Testet ob ein Account-Creation wegen einer bereits registrierten Email fehlschlägt
     */
    @Test
    public void checkCreationFailedEmailAlreadyRegistered(){
        // User in DB
        final User user = TestUtil.generateValidFakeUser();
        UserDB.getUsers().add(user);
        // Try to create same user with same email address
        User newUser = TestUtil.generateValidFakeUser();
        newUser.setEmail(user.getEmail());
        try {
            UserDB.createUser(newUser, newUser.getPassword());
            assertTrue(false);
        } catch (UserDBException e) {
            assertTrue(true);
            assertEquals("Email address already registered", e.getMessage());
        }
    }

    /**
     * Testet, ob ein Account-Creation wegen eines bereits registrierten Nickname fehlschlägt
     */
    @Test
    public void checkCreationFailedNicknameAlreadyRegistered() {
        // User in DB
        final User user = TestUtil.generateValidFakeUser();
        UserDB.getUsers().add(user);
        // Try to create same user with same nickname
        User newUser = TestUtil.generateValidFakeUser();
        newUser.setNickname(user.getNickname());
        try {
            UserDB.createUser(newUser, newUser.getPassword());
            assertTrue(false);
        } catch (UserDBException e) {
            assertTrue(true);
            assertEquals( "Nickname already in use", e.getMessage());
        }
    }

    /**
     * Testet, ob ein Account-Creation wegen einer nicht validen Email fehlschlägt
     */
    @Test
    public void checkCreationFailedEmailBad(){
        final User user = TestUtil.generateValidFakeUser();
        user.setEmail("bademail");
        try {
            UserDB.createUser(user, user.getPassword());
            assertTrue(false);
        } catch (UserDBException e) {
            assertEquals("Email address is not valid", e.getMessage());
        }
    }

    /**
     * Testet, ob ein Account-Creation wegen einem nicht validen Passwort fehlschlägt
     */
    @Test
    public void checkCreationFailedPasswordBad(){
        final User user = TestUtil.generateValidFakeUser();
        user.setPassword("badpassword");
        try {
            UserDB.createUser(user, user.getPassword());
            assertTrue(false);
        } catch (UserDBException e) {
            assertEquals("Password doesn't meet the requirements", e.getMessage());
        }
    }

    /**
     * Testet, ob ein Account-Creation wegen einem falschen Password-Retype fehlschlägt
     */
    @Test
    public void checkCreationFailedPasswordRetypeWrong() {
        final User user = TestUtil.generateValidFakeUser();
        try {
            UserDB.createUser(user, "anotherpassword");
            assertTrue(false);
        } catch (UserDBException e) {
            assertEquals("Passwords don't match", e.getMessage());
        }
    }


}
