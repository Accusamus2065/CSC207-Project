package entity.chat;
import java.util.Arrays;
import java.util.List;

public class Conversation {
    List<String> participants;
    List<Message> messages;

    public Conversation(String user1, String user2, List<Message> messages) {
        String[] arr = {user1, user2};
        this.participants = Arrays.asList(arr);
        this.messages = messages;
    }

    public void addMessage(Message message) {
        messages.add(message);
    }

    public void addParticipant(String participant) {
        participants.add(participant);
    }

    public List<String> getParticipants() {
        return participants;
    }

    public void setMessages(List<Message> messages) {
        this.messages = messages;
    }

    public List<Message> getMessages() {
        return messages;
    }
}