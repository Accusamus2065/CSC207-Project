package use_case.chat;

import entity.chat.Conversation;
import entity.chat.Message;
import interface_adapter.chat.ConversationState;

import java.util.List;

public class ConversationOutputData {
    private List<Message> list;
    public ConversationOutputData(List<Message> list) {
        this.list = list;
    }

    public List<Message> getMessages() {
        return list;
    }
}