package org.example;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UserTest {

    @Test
    void sendMessage() {
        ChatServer chatServer = new ChatServer();
        User user1 = new User("Test1", chatServer);
        User user2 = new User("Test2", chatServer);
        chatServer.registerUser(user1);
        chatServer.registerUser(user2);


        user1.sendMessage(new User[]{user2}, "Test Message");
        Message sentMessage = user1.getChatHistory().getLastMessageSent(user1.getMessageMemento());
        assertEquals(true, user2.getChatHistory().getCurrentHistory().contains(sentMessage));
    }

    @Test
    void undoMessage() {
        ChatServer chatServer = new ChatServer();
        User user1 = new User("Test1", chatServer);
        User user2 = new User("Test2", chatServer);
        chatServer.registerUser(user1);
        chatServer.registerUser(user2);


        user1.sendMessage(new User[]{user2}, "Test Message");
        Message message = user1.getChatHistory().getLastMessageSent(user1.getMessageMemento());
        user1.undoMessage();
        assertEquals(true,!user1.getChatHistory().getCurrentHistory().contains(message)
                && !user2.getChatHistory().getCurrentHistory().contains(message));
    }

    @Test
    void receiveMessage() {
        ChatServer chatServer = new ChatServer();
        User user1 = new User("Test1", chatServer);
        User user2 = new User("Test2", chatServer);
        Message testMessage = new Message(user2, new User[]{user1}, "test message");

        user1.receiveMessage(testMessage);
        assertEquals(user1.getChatHistory().getCurrentHistory().get(user1.getChatHistory().getCurrentHistory().size()-1), testMessage);
    }
}