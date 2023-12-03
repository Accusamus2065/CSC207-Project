//package use_case.chat.save;
//
//import entity.chat.Message;
//
//import java.util.Date;
//
//public class ConversationSaveInputData {
//    final private Message message;
//    public ConversationSaveInputData(String sender, String receiver, String messageContent) {
//        this.message = new Message(sender, receiver, messageContent, new Date());
//    }
//    public Message getMessage() {
//        return message;
//    }
//}

package use_case.chat.save;

import entity.chat.Message;

import java.util.Date;

/**
 * The ConversationSaveInputData class represents the input data for the conversation save use case.
 * It encapsulates information such as the sender, receiver, message content, and timestamp needed for the save operation.
 */
public class ConversationSaveInputData {

    // The message object containing sender, receiver, message content, and timestamp
    final private Message message;

    /**
     * Constructor for ConversationSaveInputData.
     * Initializes the input data with the sender, receiver, message content, and a timestamp.
     *
     * @param sender         The sender of the conversation.
     * @param receiver       The receiver of the conversation.
     * @param messageContent The content of the message to be saved.
     */
    public ConversationSaveInputData(String sender, String receiver, String messageContent) {
        // Create a new Message object with sender, receiver, message content, and current timestamp
        this.message = new Message(sender, receiver, messageContent, new Date());
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
