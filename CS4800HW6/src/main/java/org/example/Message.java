package org.example;


import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Message {
    private String messageContent;
    private User sender;
    private User[] recipients;
    private String timeStamp;

    public Message(User sender, User[] recipients, String messageContent){
        this.sender = sender;
        this.recipients = recipients;
        this.messageContent = messageContent;

        LocalDateTime currentDateTime = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        this.timeStamp = currentDateTime.format(formatter);;
    }
    public User getSender(){
        return this.sender;
    }
    public String getTimeStamp(){
        return timeStamp;
    }
    public String getMessageContent(){
        return messageContent;
    }
    public User[] getRecipients(){
        return recipients;
    }

}
