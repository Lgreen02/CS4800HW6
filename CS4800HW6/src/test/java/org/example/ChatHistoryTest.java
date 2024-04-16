package org.example;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class ChatHistoryTest {

    @Test
    void getCurrentHistoryTest() {
        ChatServer chatServer = new ChatServer();
        User user1 = new User("Test1", chatServer);
        ArrayList<Message> testHistory = new ArrayList<>();
        for(int i=0; i< testHistory.size();i++)
            assertEquals(testHistory.get(i), user1.getChatHistory().getCurrentHistory());

    }

    @Test
    void updateChatHistoryTest() {
        ChatServer chatServer = new ChatServer();
        User user1 = new User("Test1", chatServer);

        Message testMessage = new Message(user1, new User[]{},"Test Message" );
        user1.getChatHistory().updateChatHistory(testMessage);
        ArrayList<Message> testHistory = user1.getChatHistory().getCurrentHistory();
        assertEquals(testMessage, testHistory.get(testHistory.size()-1));
    }

    @Test
    void getLastMessageSentTest() {
        ChatServer chatServer = new ChatServer();
        User user1 = new User("Test1", chatServer);
        Message testMessage = new Message(user1, new User[]{},"Test Message" );
        MessageMemento testMemento = new MessageMemento(testMessage.getMessageContent(), testMessage.getTimeStamp());
        user1.getChatHistory().updateChatHistory(testMessage);
        assertEquals(testMessage, user1.getChatHistory().getLastMessageSent(testMemento));

    }

    @Test
    void removeMessageTest() {
        ChatServer chatServer = new ChatServer();
        User user1 = new User("Test1", chatServer);
        Message testMessage = new Message(user1, new User[]{},"Test Message" );
        user1.getChatHistory().updateChatHistory(testMessage);
        user1.getChatHistory().removeMessage(testMessage);
        assertEquals(false, user1.getChatHistory().getCurrentHistory().contains(testMessage));

    }
}