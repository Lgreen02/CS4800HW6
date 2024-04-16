package org.example;

import java.util.ArrayList;
import java.util.Iterator;

public class SearchMessagesByUser implements Iterator {
    private User mainUser;
    private User searchUser;
    private int messagesIndex;
    private int messageHistorySize;
    private ArrayList<Message> messages;
    public SearchMessagesByUser(User mainUser, User searchUser){
        this.mainUser = mainUser;
        this.searchUser = searchUser;
        this.messagesIndex = 0;
        this.messages = mainUser.getChatHistory().getCurrentHistory();
        this.messageHistorySize = messages.size();
    }
    @Override
    public boolean hasNext() {

        while(messagesIndex < messageHistorySize){
            if(messages.get(messagesIndex).getSender() == searchUser){
                return true;
            }
            else{
                messagesIndex++;
            }
        }
        messagesIndex = 0;
        return false;

    }

    @Override
    public Object next() {
        if(hasNext()) {
            return messages.get(messagesIndex++).getMessageContent();

        }
        return null;
    }
}
