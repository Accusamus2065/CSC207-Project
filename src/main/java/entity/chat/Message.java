package entity.chat;

import org.bson.Document;
import org.bson.codecs.pojo.annotations.BsonProperty;

import java.util.Date;

public class Message {
    @BsonProperty("sender")
    private String sender;
    @BsonProperty("receiver")
    private String receiver;
    @BsonProperty("content")
    private String content;
    @BsonProperty("timestamp")
    private Date timestamp;

    public Message () {}

    public Message(String sender, String receiver, String content) {
        this.sender = sender;
        this.receiver = receiver;
        this.content = content;
        this.timestamp = new Date(); // Automatically set the timestamp to the current date and time
    }

    public void setSender(String sender) {
        this.sender = sender;
    }

    public void setReceiver(String receiver) {
        this.receiver = receiver;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void setTimestamp() {
        this.timestamp = new Date();
    }
    public String getSender() {
        return sender;
    }

    public String getReceiver() {
        return receiver;
    }

    public String getContent() {
        return content;
    }

    public Date getTimestamp() {
        return timestamp;
    }

    public Document toDocument() {
        Document document = new Document();
        document.append("sender", sender); // Assuming User class has a toDocument method
        document.append("receiver", receiver); // Assuming User class has a toDocument method
        document.append("content", content);
        document.append("timestamp", timestamp);

        return document;
    }
}
