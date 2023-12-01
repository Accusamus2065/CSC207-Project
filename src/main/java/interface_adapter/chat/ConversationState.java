package interface_adapter.chat;

import entity.chat.Conversation;
import entity.chat.Message;

import java.util.List;

public class ConversationState {

    private List<Message> messages;

    public ConversationState(ConversationState copy) {
        messages = copy.messages;
    }

    // Because of the previous copy constructor, the default constructor must be explicit.
    public ConversationState() {
    }

    public List<Message> getMessages() {
        return messages;
    }

    public void setMessages(List<Message> messages) {
        this.messages = messages;
    }


}
