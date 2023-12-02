package entity;

import data_access.ConvoDAOImpl;
import entity.chat.Message;
import org.junit.Test;
import java.util.List;

public class TestMessage {
    ConvoDAOImpl dao = new ConvoDAOImpl();

    @Test
    public void testStoreMessage() {
        String docId = "Harry";
        String patId = "Marshal";
        Message message = new Message(docId, patId, "It's Marshal, nice to meet", null);
        dao.save(message);
    }

    @Test
    public void testRetrieveAllMessages() {
        List<Message> list = dao.query();
        for (Message m : list) {
            System.out.println(m.getSender() + " " + m.getContent());
        }
    }

    @Test
    public void testRetrieveMessagesBySender() {
        List<Message> list = dao.query("Harry", "Marshal");
        for (Message m : list) {
            System.out.println(m.getSender() + " " + m.getContent());
        }
    }

    @Test
    public void testDeleteAll() {
        dao.deleteAll();
    }
}
