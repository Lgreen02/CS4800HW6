package org.example;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

public class User implements IteratableByUser{

    private String name;
    private ChatServer chatServer;
    private HashMap<String, User> blockedUsers = new HashMap<>();
    private MessageMemento messageMemento = new MessageMemento("", "");
    private ChatHistory chatHistory = new ChatHistory(this);
    public User(String name, ChatServer chatServer){
        this.name = name;
        this.chatServer = chatServer;

    }
    public MessageMemento getMessageMemento(){
        return messageMemento;
    }
    public String getName(){
        return name;
    }
    public ChatHistory getChatHistory(){
        return chatHistory;
    }
    public void sendMessage(User[] recipients, String messageContent){
        Message message = new Message(this, recipients, messageContent);
        messageMemento.setMessageContent(messageContent);
        messageMemento.setTimeStamp(message.getTimeStamp());
        chatHistory.updateChatHistory(message);
        chatServer.sendMessage(message);
    }
    public void undoMessage(){
        chatServer.undoMessage(this);
    }
    public HashMap<String, User> getBlockedUsers(){
        return blockedUsers;
    }

    @Override
    public Iterator iterator(User userToSearchWith) {
        return chatHistory.iterator(userToSearchWith);
    }
    public void receiveMessage(Message message){
        System.out.println("[" + message.getTimeStamp() + "] " + message.getSender().getName() + ": " + message.getMessageContent());
        chatHistory.updateChatHistory(message);
    }
}
