package data_access;

import com.mongodb.*;
import com.mongodb.client.*;
import com.mongodb.client.model.Filters;
import com.mongodb.client.result.DeleteResult;
import entity.chat.Message;
import org.bson.Document;
import org.bson.codecs.configuration.CodecRegistry;
import org.bson.codecs.pojo.PojoCodecProvider;
import org.bson.conversions.Bson;
import org.jetbrains.annotations.NotNull;
import io.github.cdimascio.dotenv.Dotenv;
import use_case.chat.ConversationUserDataAccessInterface;

import java.util.ArrayList;
import java.util.List;

import static org.bson.codecs.configuration.CodecRegistries.fromProviders;
import static org.bson.codecs.configuration.CodecRegistries.fromRegistries;

/**
 * Implementation of the ConversationUserDataAccessInterface for MongoDB.
 * Handles CRUD operations for chat messages in a MongoDB database.
 */
public class ConvoDAOImpl implements ConversationUserDataAccessInterface {

    // Method to create MongoClientSettings with connection settings
    @NotNull
    private static MongoClientSettings getMongoClientSettings() {
        // Load environment variables using Dotenv
        Dotenv dotenv = Dotenv.configure().load();

        // Get MongoDB connection string from environment variables
        String connectionString = dotenv.get("CONNECTION_STRING");

        // Create a server API version
        ServerApi serverApi = ServerApi.builder().version(ServerApiVersion.V1).build();

        // Build and return MongoClientSettings
        assert connectionString != null;
        return MongoClientSettings.builder()
                .applyConnectionString(new ConnectionString(connectionString))
                .serverApi(serverApi)
                .build();
    }

    // Method to connect to MongoDB and set up codec registry
    private static MongoDatabase connectToDatabase() {
        // Get MongoClientSettings
        MongoClientSettings settings = getMongoClientSettings();
        // Create MongoClient with specified settings
        MongoClient mongoClient = MongoClients.create(settings);

        // Set up PojoCodecProvider for automatic mapping of POJOs
        CodecRegistry pojoCodecRegistry = fromRegistries(
                MongoClientSettings.getDefaultCodecRegistry(),
                fromProviders(PojoCodecProvider.builder().automatic(true).build())
        );
        // Get the database and apply the codec registry
        return mongoClient.getDatabase("entities").withCodecRegistry(pojoCodecRegistry);
    }

    // Constructor for ConvoDAOImpl
    public ConvoDAOImpl() throws MongoException {
        // Constructor logic
    }

    // Implementation of interface method to save a message
    // TODO:interface method
    public void save(Message message) {
        saveRemote(message);
    }

    // Implementation of interface method to query messages between two users
    // TODO:interface method
    public List<Message> query(String user1, String user2) {
        return getMessagesForSinglePerson(user1, user2);
    }

    // Implementation of interface method to delete all messages
    // TODO:interface method
    public void deleteAll() {
        deleteAllMessages();
    }

    // Implementation of interface method to query all messages
    // TODO:interface method
    public List<Message> query() {
        return getMessagesFromCollection();
    }

    // Method to retrieve messages between two users
    private List<Message> getMessagesForSinglePerson(String user1, String user2) {
        MongoDatabase database = connectToDatabase();
        MongoCollection<Message> messageCollection = database.getCollection("message", Message.class);
        // Create a filter to find messages between user1 and user2
        Bson filter = Filters.or(
                Filters.and(Filters.eq("sender", user1), Filters.eq("receiver", user2)),
                Filters.and(Filters.eq("receiver", user1), Filters.eq("sender", user2))
        );
        // Find messages based on the filter
        FindIterable<Message> messageIterable = messageCollection.find(filter);
        // Convert iterable to a list and return
        List<Message> list = new ArrayList<>();
        messageIterable.into(list);
        return list;

    }

    // Method to retrieve all messages from the collection
    private List<Message> getMessagesFromCollection() {
        MongoDatabase database = connectToDatabase();
        MongoCollection<Message> messageCollection = database.getCollection("message", Message.class);
        // Find all messages in the collection
        FindIterable<Message> messageIterable = messageCollection.find();
        // Convert iterable to a list and return
        List <Message> list = new ArrayList<>();
        System.out.println(list.toString());
        messageIterable.into(list);
        return list;
    }

    // Method to save a message to the remote MongoDB collection
    private void saveRemote(Message msg) {
        MongoDatabase database = connectToDatabase();
        MongoCollection<Document> collection = database.getCollection("message");
        // Create a Document from the Message object
        Document doc = new Document();
        doc.put("sender", msg.getSender());
        doc.put("receiver", msg.getReceiver());
        doc.put("content", msg.getContent());
        doc.put("timestamp", msg.getTimestamp());
        // Insert the Document into the collection
        collection.insertOne(doc);
        System.out.println("Added messages in collection message");
    }
    // Method to delete all messages from the collection
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


