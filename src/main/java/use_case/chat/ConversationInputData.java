package use_case.chat;

import entity.chat.Conversation;
import entity.chat.Message;
import entity.people.User;

public class ConversationInputData {
    final private Message message;

    public ConversationInputData(String messageContent, User sender, User receiver) {
        this.message = new Message(sender, receiver, messageContent);
    }

    Message getMessage() {
        return message;
    }
}