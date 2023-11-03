package entity.chat;

import entity.chat.Message;
import entity.people.User;

import java.util.ArrayList;
import java.util.List;

public class Conversation {
    private List<User> participants;
    private List<Message> messages;

    public Conversation() {
        this.participants = new ArrayList<>();
        this.messages = new ArrayList<>();
    }

    // Add a participant to the chat
    public void addParticipant(User user) {
        participants.add(user);
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

}
