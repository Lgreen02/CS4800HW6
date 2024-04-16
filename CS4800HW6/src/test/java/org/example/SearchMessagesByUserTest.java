package org.example;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class SearchMessagesByUserTest {

    @Test
    void hasNext() {

        ChatServer chatServer = new ChatServer();
        User user1 = new User("Test1", chatServer);
        User user2 = new User("Test2", chatServer);
        chatServer.registerUser(user1);
        chatServer.registerUser(user2);

        user1.sendMessage(new User[]{user2}, "Test Message");

        SearchMessagesByUser iterator = new SearchMessagesByUser(user2, user1);
        assertEquals(true, iterator.hasNext());

    }

    @Test
    void next() {
        ChatServer chatServer = new ChatServer();
        User user1 = new User("Test1", chatServer);
        User user2 = new User("Test2", chatServer);
        chatServer.registerUser(user1);
        chatServer.registerUser(user2);

        user1.sendMessage(new User[]{user2}, "Test Message");
        Message testMessage = user1.getChatHistory().getLastMessageSent(user1.getMessageMemento());
        SearchMessagesByUser iterator = new SearchMessagesByUser(user2, user1);
        assertEquals(testMessage.getMessageContent(), iterator.next());

    }
}