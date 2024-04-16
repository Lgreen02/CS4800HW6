package org.example;

import java.util.ArrayList;
import java.util.HashMap;

public class ChatServer {
    private HashMap<String, User> registeredUsers = new HashMap<>();

    public ChatServer(){

    }
    public HashMap<String, User> getRegisteredUsers(){
        return registeredUsers;
    }
    public void registerUser(User newUser){
        if(!registeredUsers.containsKey(newUser.getName())) {
            registeredUsers.put(newUser.getName(), newUser);
            System.out.println("Registered " + newUser.getName() + " to the server");
        }
        else{
            System.out.println("User with the name " + newUser.getName() + " has already been registered");
        }
    }
    public void unRegisterUser(User newUser){
        registeredUsers.remove(newUser.getName());
    }
    public void blockUser(User user, User blockedUser){
        if(!user.getBlockedUsers().containsKey(blockedUser.getName())){
            user.getBlockedUsers().put(blockedUser.getName(), blockedUser);
            System.out.println("You have successfully blocked " + blockedUser.getName());
        }
        else{
            System.out.println("User by the name of " + blockedUser.getName() + " has already been blocked");
        }
    }
    public void sendMessage(Message message){
        if(registeredUsers.containsKey(message.getSender().getName())) {
            for (User recipient : message.getRecipients()) {
                if(!recipient.getBlockedUsers().containsKey(message.getSender().getName())) {
                    System.out.println(message.getSender().getName() + " sent a message to " + recipient.getName());
                    recipient.receiveMessage(message);
                }
                else{
                    System.out.println("Message could not be sent to " + recipient.getName());
                }
            }
        }
        else{
            System.out.println("You must be a registered user to send messages");
        }
    }
    public void undoMessage(User user){
        MessageMemento message = user.getMessageMemento();
        Message messageToUndo = user.getChatHistory().getLastMessageSent(message);

        user.getChatHistory().removeMessage(messageToUndo);
        for(User recipient: messageToUndo.getRecipients()){
            System.out.println(user.getName() + " unsent a message");
            recipient.getChatHistory().removeMessage(messageToUndo);
        }

        System.out.println("Message unsent successfully");
    }

}
