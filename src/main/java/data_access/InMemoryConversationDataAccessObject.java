package data_access;

import entity.chat.Message;
import use_case.chat.ConversationUserDataAccessInterface;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * An in-memory implementation of the Conversation DAO that represents a single conversation
 * between a doctor and a patient. For testing purposes only.
 */
public class InMemoryConversationDataAccessObject implements ConversationUserDataAccessInterface {
    /**
     * String[] must take the form {sender, receiver}
     */
    public HashMap<List<String>, List<Message>> messages = new HashMap<>();

    @Override
    public void save(Message msg) {
        List<String> speakers = List.of(msg.getSender(), msg.getReceiver());
        List<Message> messageList;
        if (messages.containsKey(speakers)) {
             messageList = messages.get(speakers);

        } else {
            messageList = new ArrayList<>();
        }
        messageList.add(msg);
        messages.put(speakers, messageList);
    }

    /**
     * Precondition: {user1, user2} is a valid key
     */
    @Override
    public List<Message> query(String user1, String user2) {
        List<String> speakers = List.of(user1, user2);
        return messages.get(speakers);
    }

    @Override
    public void deleteAll() {
        messages.clear();
    }

    @Override
    public List<Message> query() {
        return null;
    }
}
