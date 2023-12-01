package use_case.chat;


import entity.chat.Conversation;
import entity.chat.Message;

import java.util.List;

public interface IConvoDAO {
    void save(Message message);

    List<Message> query(String user1, String user2);

    void deleteAll();

    List<Message> query();
}
