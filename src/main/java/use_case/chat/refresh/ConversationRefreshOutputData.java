package use_case.chat.refresh;

import entity.chat.Message;

import java.util.List;

public class ConversationRefreshOutputData {
    private List<Message> list;
    public ConversationRefreshOutputData(List<Message> list) {
        this.list = list;
    }
    public List<Message> getMessages() {
        return list;
    }
}
