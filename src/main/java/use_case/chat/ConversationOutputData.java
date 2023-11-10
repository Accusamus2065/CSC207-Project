package use_case.chat;

import entity.chat.Conversation;
import interface_adapter.chat.ConversationState;

public class ConversationOutputData {
    private Conversation convo;
    private boolean useCaseFailed;
    public ConversationOutputData(Conversation convo, boolean useCaseFailed) {
        this.convo = convo;
        this.useCaseFailed = useCaseFailed;
    }

    public Conversation getConversation() {
        return convo;
    }
}
