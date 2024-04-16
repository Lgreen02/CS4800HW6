package org.example;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ChatServerTest {

    @Test
    void registerUserTest() {
        ChatServer chatServer = new ChatServer();
        User user = new User("Test", chatServer);
        chatServer.registerUser(user);
        assertEquals(true, chatServer.getRegisteredUsers().containsKey(user.getName()));
    }
    @Test
    void registerUserTwiceTest() {
        ChatServer chatServer = new ChatServer();
        User user = new User("Test", chatServer);
        chatServer.registerUser(user);
        chatServer.registerUser(user);
        assertEquals(true, chatServer.getRegisteredUsers().containsKey(user.getName()));
    }

    @Test
    void unRegisterUserTest() {
        ChatServer chatServer = new ChatServer();
        User user = new User("Test", chatServer);
        chatServer.registerUser(user);
        chatServer.unRegisterUser(user);
        assertEquals(false, chatServer.getRegisteredUsers().containsKey(user.getName()));
    }

    @Test
    void blockUserTest() {
        ChatServer chatServer = new ChatServer();
        User user1 = new User("Test1", chatServer);
        User user2 = new User("Test2", chatServer);
        chatServer.blockUser(user1, user2);
        assertEquals(true, user1.getBlockedUsers().containsKey(user2.getName()));
    }
    @Test
    void blockUserTwiceTest() {
        ChatServer chatServer = new ChatServer();
        User user1 = new User("Test1", chatServer);
        User user2 = new User("Test2", chatServer);
        chatServer.blockUser(user1, user2);
        chatServer.blockUser(user1, user2);
        assertEquals(true, user1.getBlockedUsers().containsKey(user2.getName()));
    }

    @Test
    void sendMessageTest() {
        ChatServer chatServer = new ChatServer();
        User user1 = new User("Test1", chatServer);
        User user2 = new User("Test2", chatServer);
        chatServer.registerUser(user1);
        chatServer.registerUser(user2);
        Message message = new Message(user1, new User[]{user2}, "Test Message");
        chatServer.sendMessage(message);
        assertEquals(message, user2.getChatHistory().getCurrentHistory().get(user2.getChatHistory().getCurrentHistory().size()-1));
    }
    @Test
    void sendMessageUnregisteredTest() {
        ChatServer chatServer = new ChatServer();
        User user1 = new User("Test1", chatServer);
        User user2 = new User("Test2", chatServer);

        Message message = new Message(user1, new User[]{user2}, "Test Message");
        chatServer.sendMessage(message);
        assertEquals(false, user2.getChatHistory().getCurrentHistory().contains(message));
    }
    @Test
    void sendMessageBlockedTest() {
        ChatServer chatServer = new ChatServer();
        User user1 = new User("Test1", chatServer);
        User user2 = new User("Test2", chatServer);
        chatServer.registerUser(user1);
        chatServer.registerUser(user2);
        chatServer.blockUser(user2, user1);

        Message message = new Message(user1, new User[]{user2}, "Test Message");
        chatServer.sendMessage(message);
        assertEquals(false, user2.getChatHistory().getCurrentHistory().contains(message));
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
        chatServer.undoMessage(user1);
        assertEquals(true,!user1.getChatHistory().getCurrentHistory().contains(message)
                && !user2.getChatHistory().getCurrentHistory().contains(message));
    }
}