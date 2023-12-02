package interface_adapter.chat.refresh;

import entity.chat.Message;

import java.util.List;

public class ConversationRefreshState {
    private String sender;
    private String receiver;
    private List<Message> messages;

    public ConversationRefreshState(ConversationRefreshState copy) {
        messages = copy.messages;
    }

    // Because of the previous copy constructor, the default constructor must be explicit.
    public ConversationRefreshState() {
    }

    public List<Message> getMessages() {
        return messages;
    }

    public void setMessages(List<Message> messages) {
        this.messages = messages;
    }

    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }

    public String getReceiver() {
        return receiver;
    }

    public void setReceiver(String receiver) {
        this.receiver = receiver;
    }
}
