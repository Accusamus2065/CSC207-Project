package entity.chat;

import entity.people.User;

import java.util.Date;

public class Message {
    private User sender;
    private User receiver;
    private String content;
    private Date timestamp;

    public Message(User sender, User receiver, String content) {
        this.sender = sender;
        this.receiver = receiver;
        this.content = content;
        this.timestamp = new Date(); // Automatically set the timestamp to the current date and time
    }

    public User getSender() {
        return sender;
    }

    public User getReceiver() {
        return receiver;
    }

    public String getContent() {
        return content;
    }

    public Date getTimestamp() {
        return timestamp;
    }
}
