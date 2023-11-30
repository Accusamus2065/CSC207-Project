package data_access;

import com.mongodb.*;
import com.mongodb.client.*;
import com.mongodb.client.model.Aggregates;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.changestream.ChangeStreamDocument;
import com.mongodb.client.result.DeleteResult;
import entity.chat.Conversation;
import entity.chat.Message;
import org.bson.Document;
import org.bson.codecs.configuration.CodecRegistry;
import org.bson.codecs.pojo.PojoCodecProvider;
import org.bson.conversions.Bson;
import org.jetbrains.annotations.NotNull;
import io.github.cdimascio.dotenv.Dotenv;
import use_case.chat.IConvoDAO;
import view.ConversationView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.atomic.AtomicReference;

import static org.bson.codecs.configuration.CodecRegistries.fromProviders;
import static org.bson.codecs.configuration.CodecRegistries.fromRegistries;

public class ConvoDAOImpl implements IConvoDAO {

    @NotNull
    private static MongoClientSettings getMongoClientSettings() {
        Dotenv dotenv = Dotenv.configure().load();

        String connectionString = dotenv.get("CONNECTION_STRING");

        ServerApi serverApi = ServerApi.builder().version(ServerApiVersion.V1).build();

        assert connectionString != null;
        return MongoClientSettings.builder()
                .applyConnectionString(new ConnectionString(connectionString))
                .serverApi(serverApi)
                .build();
    }

    private static MongoDatabase connectToDatabase() {
        MongoClientSettings settings = getMongoClientSettings();
        MongoClient mongoClient = MongoClients.create(settings);

        CodecRegistry pojoCodecRegistry = fromRegistries(
                MongoClientSettings.getDefaultCodecRegistry(),
                fromProviders(PojoCodecProvider.builder().automatic(true).build())
        );

        return mongoClient.getDatabase("entities").withCodecRegistry(pojoCodecRegistry);
    }

    public ConvoDAOImpl() throws MongoException {
        // Constructor logic
    }

    // TODO:interface method
    public void save(Message message) {
        saveRemote(message);
    }

    // TODO:interface method
    public List<Message> query(String user1, String user2) {
        return getMessagesForSinglePerson(user1, user2);
    }

    // TODO:interface method
    public void deleteAll() {
        deleteAllMessages();
    }


    // TODO:interface method
    public List<Message> query() {
        return getMessagesFromCollection();
    }


    private List<Message> getMessagesForSinglePerson(String user1, String user2) {
        MongoDatabase database = connectToDatabase();
        MongoCollection<Message> messageCollection = database.getCollection("message", Message.class);
        Bson filter = Filters.or(
                Filters.and(Filters.eq("sender", user1), Filters.eq("receiver", user2)),
                Filters.and(Filters.eq("receiver", user1), Filters.eq("sender", user2))
        );
        FindIterable<Message> messageIterable = messageCollection.find(filter);
        List<Message> list = new ArrayList<>();
        messageIterable.into(list);
        return list;

    }

    private List<Message> getMessagesFromCollection() {
        MongoDatabase database = connectToDatabase();
        MongoCollection<Message> messageCollection = database.getCollection("message", Message.class);
        FindIterable<Message> messageIterable = messageCollection.find();
        List <Message> list = new ArrayList<>();
        System.out.println(list.toString());
        messageIterable.into(list);
        return list;
    }

    private void saveRemote(Message msg) {
        MongoDatabase database = connectToDatabase();
        MongoCollection<Document> collection = database.getCollection("message");
        Document doc = new Document();
        doc.put("sender", msg.getSender());
        doc.put("receiver", msg.getReceiver());
        doc.put("content", msg.getContent());
        doc.put("timestamp", msg.getTimestamp());

        collection.insertOne(doc);
        System.out.println("Added messages in collection message");
    }

    private void deleteAllMessages() {
        MongoDatabase database = connectToDatabase();
        MongoCollection<Message> messageCollection = database.getCollection("message", Message.class);

        // Create an empty filter to match all documents
        Bson filter = Filters.eq("_id", new Document("$exists", true)); // Assuming "_id" field exists in your documents

        // Perform the delete operation
        DeleteResult result = messageCollection.deleteMany(filter);

        // Print the number of deleted documents
        System.out.println("Deleted " + result.getDeletedCount() + " documents from the 'message' collection");

    }

}
