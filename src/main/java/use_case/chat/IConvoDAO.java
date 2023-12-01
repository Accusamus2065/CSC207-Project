package use_case.chat;


import entity.chat.Conversation;
import entity.chat.Message;

import java.util.List;

public interface IConvoDAO {

    void save(Message msg);

    List<Message> query(String user1, String user2);

    void deleteAll(); // TODO: deleteAll()

    List<Message> query();
}
