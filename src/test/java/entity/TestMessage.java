package entity;

import data_access.ConvoDAOImpl;
import entity.chat.Message;
import entity.people.Doctor;
import entity.people.Patient;
import entity.people.User;
import org.junit.Test;

import java.util.Date;

public class TestMessage {
    @Test
    public void testStoreMessage() {
        Message message = new Message(new Doctor("22","22"),new Patient("22", "22"), "Hi I'm John.");
        ConvoDAOImpl dao = new ConvoDAOImpl();
        dao.save(message);
    }
}
