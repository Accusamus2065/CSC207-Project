package use_case.chat;

import entity.chat.Message;

import java.util.List;

/**
 * The ConversationUserDataAccessInterface interface defines the contract for accessing conversation data.
 * It declares methods for saving messages, querying messages based on user interactions, deleting all messages, and querying all messages.
 */
public interface ConversationUserDataAccessInterface {

    /**
     * Saves a message in the conversation data.
     *
     * @param msg The message to be saved.
     */
    void save(Message msg);

    /**
     * Queries messages based on the interaction between two users.
     *
     * @param user1 The first user involved in the conversation.
     * @param user2 The second user involved in the conversation.
     * @return The list of messages exchanged between the two users.
     */
    List<Message> query(String user1, String user2);

    /**
     * Deletes all messages in the conversation data.
     */
    void deleteAll(); // TODO: deleteAll()

    /**
     * Queries all messages in the conversation data.
     *
     * @return The list of all messages in the conversation.
     */
    List<Message> query();
}