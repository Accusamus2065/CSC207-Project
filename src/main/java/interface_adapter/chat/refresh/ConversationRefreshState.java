package interface_adapter.chat.refresh;

import entity.chat.Message;

import java.util.List;

/**
 * The ConversationRefreshState represents the state of a conversation refresh view.
 * It includes information such as the sender, receiver, and a list of messages.
 */
public class ConversationRefreshState {
    // Sender of the conversation
    private String sender;

    // Receiver of the conversation
    private String receiver;

    private Boolean isDoctor;

    // List of messages in the conversation
    private List<Message> messages;

    /**
     * Copy constructor for creating a new instance based on an existing ConversationRefreshState.
     *
     * @param copy The ConversationRefreshState to copy.
     */
    public ConversationRefreshState(ConversationRefreshState copy) {
        // Copy the list of messages from the provided state
        messages = copy.messages;
    }

    /**
     * Explicit default constructor for creating an empty ConversationRefreshState.
     */
    public ConversationRefreshState() {
        // Empty constructor
    }

    /**
     * Getter for the list of messages in the conversation.
     *
     * @return The list of messages.
     */
    public List<Message> getMessages() {
        return messages;
    }

    /**
     * Setter for updating the list of messages in the conversation.
     *
     * @param messages The new list of messages.
     */
    public void setMessages(List<Message> messages) {
        this.messages = messages;
    }

    /**
     * Getter for the sender of the conversation.
     *
     * @return The sender of the conversation.
     */
    public String getSender() {
        return sender;
    }

    /**
     * Setter for updating the sender of the conversation.
     *
     * @param sender The new sender of the conversation.
     */
    public void setSender(String sender) {
        this.sender = sender;
    }

    /**
     * Getter for the receiver of the conversation.
     *
     * @return The receiver of the conversation.
     */
    public String getReceiver() {
        return receiver;
    }

    /**
     * Setter for updating the receiver of the conversation.
     *
     * @param receiver The new receiver of the conversation.
     */
    public void setReceiver(String receiver) {
        this.receiver = receiver;
    }

    public void setIsDoctor(Boolean isDoctor){
        this.isDoctor = isDoctor;
    }

    public Boolean getIsDoctor(){
        return isDoctor;
    }
}
