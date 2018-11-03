package de.berlin.saucken.aufgabe2.model;

import lombok.Data;


import java.util.ArrayList;
import java.util.List;

@Data
public class Chatroom {

    private String name;

    private String topicDescription;

    private List<User> members = new ArrayList<>();

    private List<Message> messages = new ArrayList<>();

    private boolean isActive = false;

    public Chatroom(final String name, final String topic){
        this.name = name;
        this.topicDescription = topic;
    }

    public boolean isMember(final User user) {
        return members.contains(user);
    }


    public void sendMessageToMembers(final Message message) {
        for(User user :members) {
            user.getMessagesReceived().add(message);
        }
    }
}