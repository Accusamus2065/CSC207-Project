package entity;

import data_access.ConvoDAOImpl;
import entity.chat.Conversation;
import entity.chat.Message;
import org.junit.Test;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;

public class TestMessage {
    ConvoDAOImpl dao = new ConvoDAOImpl();

    @Test
    public void testStoreMessage() {
        String docId = "Harry";
        String patId = "Marshal";
        Message message = new Message(docId, patId, "It's Marshal, nice to meet");
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
          
    @Test
    public void testMessagesForPatient() {
        String me = "fdljfsdkljfs";
        String other = "dfklsfskljdf";
        Conversation convo = dao.query(me, other);
        for (Message m : convo.getMessages()) {
            if (me.equals(m.getSender()))
                System.out.println(m.getContent());
            else
                System.out.println("\t\t" + m.getContent());
        }
    }

    @Test
    public void testMessagesForDoctor() {
        String me = "2222222222";
        String other = "f";
        Conversation convo = dao.query(me, other);
        for (Message m : convo.getMessages()) {
            if (me.equals(m.getSender()))
                System.out.println(m.getContent());
            else
                System.out.println("\t\t" + m.getContent());
        }
    }

    @Test
    public void testDeleteAll() {
        dao.deleteAll();
    }
}
