package entity.chat;

import entity.people.User;

import java.util.Date;

public class Message {
    private User sender;
    private String content;
    private Date timestamp;

    public Message(User sender, String content) {
        this.sender = sender;
        this.content = content;
        this.timestamp = new Date(); // Automatically set the timestamp to the current date and time
    }

    public User getSender() {
        return sender;
    }

    public String getContent() {
        return content;
    }

    public Date getTimestamp() {
        return timestamp;
    }

    @Override
    public String toString() {
        return "Message{" +
                "sender=" + sender +
                ", content='" + content + '\'' +
                ", timestamp=" + timestamp +
                '}';
    }
}
