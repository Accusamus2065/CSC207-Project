package data_access;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import entity.chat.Message;
import entity.mongo.MongoFactory;
import org.bson.Document;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static com.mongodb.client.model.Filters.eq;
import static org.junit.Assert.*;

public class ConvoDAOImplTest {
    private final MongoClient mongoClient = MongoFactory.setUpMongoClient();
    private final MongoDatabase database = mongoClient.getDatabase("test");
    private final MongoCollection<Document> messages = database.getCollection("message");
    private final ConvoDAOImpl convoDAO = new ConvoDAOImpl();
    private final static String SENDER = "TestConvoDAOSender";
    private final static String RECEIVER = "TestConvoDAOReceiver";
    private final static String CONTENT = "TestConvoDAOMessage";
    private final static Date DATE = new Date();

    @Test
    public void saveMessageTest() {
        Message message = new Message(SENDER, RECEIVER, CONTENT, DATE);
        convoDAO.save(message);

        Document query = new Document("sender", SENDER);
        assertNotEquals(0, messages.countDocuments(query));
        Document DAOMessage = messages.find(query).first();

        assertNotNull(DAOMessage);
        assertEquals(SENDER, DAOMessage.getString("sender"));
        assertEquals(RECEIVER, DAOMessage.getString("receiver"));
        assertEquals(CONTENT, DAOMessage.getString("content"));
        assertEquals(DATE, DAOMessage.getDate("timestamp"));

        messages.deleteMany(eq("sender", SENDER));
    }

    @Test
    public void getMessagesTest() {
        List<Message> messageList = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            Message message = new Message(SENDER + "Get", RECEIVER + "Get", CONTENT + i, DATE);
            convoDAO.save(message);
            messageList.add(message);
        }
        List<Message> DAOMessages = convoDAO.query(SENDER, RECEIVER);

        assertEquals(messageList.size(), DAOMessages.size());
        for (int i = 0; i < 5; i++) {
            Message DAOMessage = DAOMessages.get(i);
            Message message = messageList.get(i);
            assertEquals(message.getSender(), DAOMessage.getSender());
            assertEquals(message.getReceiver(), DAOMessage.getReceiver());
            assertEquals(message.getContent(), DAOMessage.getContent());
            assertEquals(message.getTimestamp(), DAOMessage.getTimestamp());
        }

        messages.deleteMany(eq("sender", SENDER));
    }

    @Test
    public void deleteAllTest() {
        convoDAO.deleteAll();
        List<Message> DAOMessages = convoDAO.query();
        assertEquals(0, DAOMessages.size());
    }
}