package org.example;

public class MessageMemento {
    private String messageContent;
    private String timeStamp;
    public MessageMemento(String messageContent, String timeStamp){
        this.messageContent = messageContent;
        this.timeStamp = timeStamp;
    }

    public String getMessageContent() {
        return messageContent;
    }
    public String getTimeStamp(){
        return timeStamp;
    }
    public void setMessageContent(String messageContent){
        this.messageContent = messageContent;
    }
    public void setTimeStamp(String timeStamp){
        this.timeStamp = timeStamp;
    }
}
