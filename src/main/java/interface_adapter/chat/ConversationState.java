package interface_adapter.chat;

import entity.chat.Conversation;

public class ConversationState {

    private Conversation conv = null;

    public ConversationState(ConversationState copy) {
        conv = copy.conv;
    }

    // Because of the previous copy constructor, the default constructor must be explicit.
    public ConversationState() {
    }

    public Conversation getConversation() {
        return conv;
    }

    public void setConversation(Conversation conv) {
        this.conv = conv;
    }

}
