package de.berlin.saucken.aufgabe2.model;

import lombok.Getter;

import java.util.ArrayList;
import java.util.List;


public abstract class UserDB {

    @Getter
    private static List<User> users = new ArrayList<>();

    /**
     * Legt einen neuen User an
     * @param user
     * @param retypedPassword
     * @throws UserDBException
     */
    public static void createUser(final User user, final String retypedPassword) throws UserDBException {
        checkInput(user, retypedPassword);
        users.add(user);
    }

    /**
     * Prüft die Eingaben des Users bei der Erstellung eines Kontos
     * @param user
     * @param retypedPassword
     * @throws UserDBException
     */
    public static void checkInput(final User user, final String retypedPassword) throws UserDBException {
        for (User userDB : users) {
            if (user.getEmail().equals(userDB.getEmail())) {
                throw new UserDBException("Email address already registered");
            }
            if (user.getNickname().equals(userDB.getNickname())) {
                throw new UserDBException("Nickname already in use");
            }
        }
        if (!user.isValidEmailAddress()) {
            throw new UserDBException("Email address is not valid");
        }
        if (!user.isValidPassword()) {
            throw new UserDBException("Password doesn't meet the requirements");
        }
        // Check if password was correctly retyped
        if (!user.isSamePassword(retypedPassword)) {
            throw new UserDBException("Passwords don't match");
        }
    }

    /**
     * Prüft die Eingabe des Users beim Login
     * @param userToCheck
     * @throws UserDBException
     */
    public static void checkInput(final User userToCheck) throws UserDBException {
        User userInDB = null;
        // Check if user with this email in DB
        for (User user : users) {
            if (user.getEmail().equals(userToCheck.getEmail())) {
                userInDB = user;
                break;
            }
        }
        // User by email not found
        if (userInDB == null) {
            throw new UserDBException("Email is not registered");
        }
        // Check if password matches
        if (!userInDB.getPassword().equals(userToCheck.getPassword())) {
            throw new UserDBException("Password is wrong");
        }

    }

}
