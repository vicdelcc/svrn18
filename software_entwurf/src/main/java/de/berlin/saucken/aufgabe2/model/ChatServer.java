package de.berlin.saucken.aufgabe2.model;

import lombok.Getter;


import java.time.LocalDateTime;
import java.util.ArrayList;

import java.util.List;

public abstract class ChatServer {

    @Getter
    private static List<User> usersConnected = new ArrayList<>();

    @Getter
    private static List<Chatroom> chatroomsActive = new ArrayList<>();

    private static LocalDateTime start;

    public static void start() {

        start = LocalDateTime.now();

        // Lädt ein paar default chat rooms
        Chatroom chatroomDogs = new Chatroom("Dog lovers", "For people who love dogs");
        chatroomDogs.setActive(true);
        Chatroom chatroomCats = new Chatroom("Cat lovers", "For people who love cats");
        chatroomCats.setActive(true);
        chatroomsActive.add(chatroomDogs);
        chatroomsActive.add(chatroomCats);
    }

    public static boolean isRunning() {
        return start != null;
    }

    public static void logUser(final User user) throws ChatSeverException, UserDBException {
        UserDB.checkInput(user);
        if (!isRunning()) {
            throw new ChatSeverException("Chat server is not running");
        }
        usersConnected.add(user);
    }

    public static void logoutUser(final User user) throws ChatSeverException {
        if (usersConnected.contains(user)) {
            usersConnected.remove(user);
        } else {
            throw new ChatSeverException("User is not logged in");
        }
    }

    /**
     * Get chatroom by object
     * @param chatroom
     * @return
     * @throws ChatSeverException
     */
    public static Chatroom getChatroom(final Chatroom chatroom) throws ChatSeverException {
        for (Chatroom chatroomActive : chatroomsActive) {
            if (chatroomActive.getName().equals(chatroom.getName())) {
                return chatroomActive;
            }
        }
        throw new ChatSeverException("Chat room not found");
    }

    /**
     * Get chatroom by name (for tests)
     * @param name
     * @return
     * @throws ChatSeverException
     */
    public static Chatroom getChatroom(final String name) throws ChatSeverException {
        for (Chatroom chatroomActive : chatroomsActive) {
            if (chatroomActive.getName().equals(name)) {
                return chatroomActive;
            }
        }
        throw new ChatSeverException("Chat room not found");
    }

    public static void addUserToChatroom(final User user, final Chatroom chatroom) throws ChatSeverException {
        getChatroom(chatroom).getMembers().add(user);
    }

    public static void removeUserFromChatrom(final User user, final Chatroom chatroom) throws ChatSeverException {
        if (chatroom.isMember(user)) {
            getChatroom(chatroom).getMembers().remove(user);
        } else {
            throw new ChatSeverException("User is not member of this chat");
        }
    }

    public static void createChatroom(final Chatroom chatroom) throws ChatSeverException {
        for (Chatroom chatroomActive : chatroomsActive) {
            if (chatroomActive.getName().equals(chatroom.getName())) {
                throw new ChatSeverException("Chat room with that name already exists");
            }
        }
        chatroom.setActive(true);
        chatroomsActive.add(chatroom);
    }

    public static void sendMessage(final Chatroom chatroom, final Message message) throws ChatSeverException {
        // Simulation chat client sends message to server
        getChatroom(chatroom).getMessages().add(message);
        // Simulation chat server sends messages to other chat clients
        chatroom.sendMessageToMembers(message);
    }


}
