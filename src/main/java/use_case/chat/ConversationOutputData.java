package use_case.chat;

import entity.chat.Conversation;
import entity.chat.Message;
import interface_adapter.chat.ConversationState;

import java.util.List;

public class ConversationOutputData {
    private List<Message> convo;
    private boolean useCaseFailed;
    public ConversationOutputData(List<Message> convo, boolean useCaseFailed) {
        this.convo = convo;
        this.useCaseFailed = useCaseFailed;
    }

    public List<Message> getConversation() {
        return convo;
    }
}
