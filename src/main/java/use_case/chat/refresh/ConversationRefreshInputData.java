//package use_case.chat.refresh;
//
//import entity.chat.Message;
//
//import javax.xml.crypto.Data;
//import java.security.Timestamp;
//import java.time.LocalDateTime;
//import java.util.Date;
//
//public class ConversationRefreshInputData {
//    final private Message message;
//    public ConversationRefreshInputData(String sender, String receiver) {
//        this.message = new Message(sender, receiver, null, null);
//    }
//    public Message getMessage() {
//        return message;
//    }
//}

package use_case.chat.refresh;

import entity.chat.Message;

/**
 * The ConversationRefreshInputData class represents the input data for the conversation refresh use case.
 * It encapsulates information such as the sender, receiver, and other details needed for the refresh operation.
 */
public class ConversationRefreshInputData {

    // The message object containing sender, receiver, and other details
    final private Message message;

    /**
     * Constructor for ConversationRefreshInputData.
     * Initializes the input data with the sender, receiver, and other details.
     *
     * @param sender   The sender of the conversation.
     * @param receiver The receiver of the conversation.
     */
    public ConversationRefreshInputData(String sender, String receiver) {
        // Create a new Message object with sender, receiver, and null values for content and timestamp
        this.message = new Message(sender, receiver, null, null);
    }

    /**
     * Getter for retrieving the Message object encapsulated in the input data.
     *
     * @return The Message object.
     */
    public Message getMessage() {
        return message;
    }
}
