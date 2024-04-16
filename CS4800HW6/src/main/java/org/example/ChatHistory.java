package org.example;

import java.util.HashMap;
import java.util.Iterator;
import java.util.ArrayList;
public class ChatHistory implements IteratableByUser{
    private ArrayList<Message> userChatHistory = new ArrayList<>();
    private User user;
    public ChatHistory(User user){
        this.user = user;
    }
    @Override
    public Iterator iterator(User userToSearchWith) {
        SearchMessagesByUser search = new SearchMessagesByUser(this.user, userToSearchWith);
        return search;
    }
    public ArrayList<Message> getCurrentHistory(){
        return userChatHistory;
    }
    public void updateChatHistory(Message message){
        userChatHistory.add(message);
    }

    public Message getLastMessageSent(MessageMemento message){ // I assumed that this was the intended use of memento
        Message lastMessage = null;
        for(int i = userChatHistory.size()-1; i>=0; i--){
            if(userChatHistory.get(i).getMessageContent() == message.getMessageContent()
                    && userChatHistory.get(i).getTimeStamp() == message.getTimeStamp()){
                lastMessage = userChatHistory.get(i);
                break;
            }
        }
        return lastMessage;
    }
    public void removeMessage(Message message){
        userChatHistory.remove(message);
    }

}
