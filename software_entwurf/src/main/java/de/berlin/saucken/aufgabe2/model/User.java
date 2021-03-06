package de.berlin.saucken.aufgabe2.model;

import de.berlin.saucken.aufgabe2.Utils.EmailValidator;
import de.berlin.saucken.aufgabe2.Utils.PasswordValidator;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class User {

    private String email;

    private String nickname;

    private String password;

    // To simulate the display of the user, to check if he/she received a message sent by another user
    private List<Message> messagesReceived = new ArrayList<>();

    public User(final String email, final String nickname, final String password) {
        this.email = email;
        this.nickname = nickname;
        this.password = password;
    }


    public boolean isValidEmailAddress() {
        return new EmailValidator().validate(email);
    }

    public boolean isValidPassword() {
        return new PasswordValidator().validate(password);
    }

    public boolean isSamePassword(String retypedPassword) {
        return password.equals(retypedPassword);
    }

}
