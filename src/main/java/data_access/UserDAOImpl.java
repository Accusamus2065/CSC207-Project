package data_access;

import com.mongodb.*;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import entity.people.User;
import entity.people.UserFactory;
import org.bson.Document;
//import use_case.login.LoginUserDataAccessInterface;
import org.jetbrains.annotations.NotNull;
import use_case.signup.SignupUserDataAccessInterface;

import java.util.HashMap;
import java.util.Map;

import io.github.cdimascio.dotenv.Dotenv;


public class UserDAOImpl{// implements SignupUserDataAccessInterface {
    private final Map<String, User> accounts = new HashMap<>();
    private UserFactory userFactory;

    @NotNull
    private static MongoClientSettings getMongoClientSettings() {
        Dotenv dotenv = Dotenv.configure().load();

        String connectionString = dotenv.get("CONNECTION_STRING");

        ServerApi serverApi = ServerApi.builder().version(ServerApiVersion.V1).build();

        assert connectionString != null;
        return MongoClientSettings.builder().applyConnectionString(
                new ConnectionString(connectionString)).serverApi(serverApi).build();
    }

    public UserDAOImpl(UserFactory userFactory) throws MongoException {
        MongoClientSettings settings = getMongoClientSettings();

        // Create a new client and connect to the server
        try (MongoClient mongoClient = MongoClients.create(settings)) {
            // Send a ping to confirm a successful connection
            MongoDatabase database = mongoClient.getDatabase("admin");
            database.runCommand(new Document("ping", 1));
            System.out.println("Pinged your deployment. You successfully connected to MongoDB!");
        }
    }

    @Override
    public boolean existsByName(String identifier) {
        return accounts.containsKey(identifier);
    }



    private void saveRemote(User user) {
        MongoClientSettings settings = getMongoClientSettings();
        try (MongoClient mongoClient = MongoClients.create(settings)) {
            MongoDatabase database = mongoClient.getDatabase("entities");
            MongoCollection<Document> patients = database.getCollection("patients");
            Document patient = new Document("username", user.getUsername())
                    .append("password", user.getPassword());
            patients.insertOne(patient);
            System.out.println("Added patient to database");
        }
    }
}