package use_case.chat;

import entity.chat.Message;

import java.util.List;

public class ConversationOutputData {
    private final List<Message> list;

    public ConversationOutputData(List<Message> list) {
        this.list = list;
    }

    public List<Message> getMessages() {
        return list;
    }
}