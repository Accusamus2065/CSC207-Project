package entity.chat;

import entity.chat.Message;
import entity.people.User;

import java.util.ArrayList;
import java.util.List;

public class Conversation {
    private List<User> participants; // List of users participating in the chat
    private List<Message> messages; // List of messages in the chat

    public Conversation(String chatName) {
        this.participants = new ArrayList<>();
        this.messages = new ArrayList<>();
    }

    // Add a participant to the chat
    public void addParticipant(User user) {
        participants.add(user);
    }

    // Remove a participant from the chat
    public void removeParticipant(User user) {
        participants.remove(user);
    }

    // Add a message to the chat
    public void addMessage(Message message) {
        messages.add(message);
    }

    // Get all messages in the chat
    public List<Message> getMessages() {
        return messages;
    }

    // Get the participants in the chat
    public List<User> getParticipants() {
        return participants;
    }

    @Override
    public String toString() {
        return "Chat{participants=" + participants +
                ", messages=" + messages +
                '}';
    }
}
