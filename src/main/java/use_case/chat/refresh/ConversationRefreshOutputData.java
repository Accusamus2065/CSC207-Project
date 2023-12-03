package use_case.chat.refresh;

import entity.chat.Message;

import java.util.List;

/**
 * The ConversationRefreshOutputData class represents the output data for the conversation refresh use case.
 * It encapsulates a list of refreshed conversation messages.
 */
public class ConversationRefreshOutputData {

    // List of refreshed conversation messages
    private List<Message> list;

    /**
     * Constructor for ConversationRefreshOutputData.
     * Initializes the output data with a list of refreshed conversation messages.
     *
     * @param list The list of refreshed conversation messages.
     */
    public ConversationRefreshOutputData(List<Message> list) {
        this.list = list;
    }

    /**
     * Getter for retrieving the list of refreshed conversation messages.
     *
     * @return The list of refreshed conversation messages.
     */
    public List<Message> getMessages() {
        return list;
    }
}
