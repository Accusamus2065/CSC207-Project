package interface_adapter.chat.refresh;

import entity.chat.Message;

import java.util.List;

public class ConversationRefreshState {
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

}
