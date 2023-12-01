package use_case.chat.refresh;

import entity.chat.Message;

import javax.xml.crypto.Data;
import java.security.Timestamp;
import java.time.LocalDateTime;
import java.util.Date;

public class ConversationRefreshInputData {
    final private Message message;
    public ConversationRefreshInputData(String sender, String receiver, String messageContent) {
        this.message = new Message(sender, receiver, messageContent, new Date());
    }
    public Message getMessage() {
        return message;
    }
}
