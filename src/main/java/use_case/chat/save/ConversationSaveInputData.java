package use_case.chat.save;

import entity.chat.Message;

import java.util.Date;

public class ConversationSaveInputData {
    final private Message message;
    public ConversationSaveInputData(String sender, String receiver, String messageContent) {
        this.message = new Message(sender, receiver, messageContent, new Date());
    }
    public Message getMessage() {
        return message;
    }
}
