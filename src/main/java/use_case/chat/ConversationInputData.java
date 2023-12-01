package use_case.chat;

import entity.chat.Message;

public class ConversationInputData {
    final private Message message;
    public ConversationInputData( String sender, String receiver, String messageContent) {
        this.message = new Message(sender, receiver, messageContent, null);
    }
    public Message getMessage() {
        return message;
    }
}
