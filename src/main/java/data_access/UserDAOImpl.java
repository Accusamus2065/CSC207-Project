package data_access;

import com.mongodb.*;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoDatabase;
import entity.people.User;
import entity.people.UserFactory;
import org.bson.Document;
//import use_case.login.LoginUserDataAccessInterface;
//import use_case.signup.SignupUserDataAccessInterface;

import java.io.*;
import java.util.HashMap;
import java.util.Map;



import io.github.cdimascio.dotenv.Dotenv;



public class UserDAOImpl { //implements SignupUserDataAccessInterface, LoginUserDataAccessInterface {
    private final Map<String, User> accounts = new HashMap<>();

    private UserFactory userFactory;

    public UserDAOImpl(String csvPath, UserFactory userFactory) throws IOException {
        this.userFactory = userFactory;

        Dotenv dotenv = Dotenv.configure().load();

        String connectionString = dotenv.get("CONNECTION_STRING");

        ServerApi serverApi = ServerApi.builder()
                .version(ServerApiVersion.V1)
                .build();

        MongoClientSettings settings = MongoClientSettings.builder()
                .applyConnectionString(new ConnectionString(connectionString))
                .serverApi(serverApi)
                .build();

        // Create a new client and connect to the server
        try (MongoClient mongoClient = MongoClients.create(settings)) {
            try {
                // Send a ping to confirm a successful connection
                MongoDatabase database = mongoClient.getDatabase("admin");
                database.runCommand(new Document("ping", 1));
                System.out.println("Pinged your deployment. You successfully connected to MongoDB!");
            } catch (MongoException e) {
                e.printStackTrace();
            }
        }
    }

//    @Override
//    public void save(User user) {
//        accounts.put(user.getUsername(), user);
//        this.save();
//    }
//
//    @Override
//    public User get(String username) {
//        return accounts.get(username);
//    }
//
//    private void save() {
//
//    }
//
//
//    /**
//     * Return whether a user exists with username identifier.
//     * @param identifier the username to check.
//     * @return whether a user exists with username identifier
//     */
//    @Override
//    public boolean existsByName(String identifier) {
//        return accounts.containsKey(identifier);
//    }

}