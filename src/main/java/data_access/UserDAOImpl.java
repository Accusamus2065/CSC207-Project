package data_access;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import entity.mongo.MongoFactory;
import entity.people.User;
import entity.people.UserFactory;
import org.bson.Document;
//import use_case.login.LoginUserDataAccessInterface;
import use_case.signup.SignupUserDataAccessInterface;

import java.util.HashMap;
import java.util.Map;

public class UserDAOImpl implements SignupUserDataAccessInterface {
    private final Map<String, User> accounts = new HashMap<>();
    private UserFactory userFactory;
    private static final MongoClient mongoClient = MongoFactory.setUpMongoClient();

    @Override
    public boolean existsByName(String identifier) {
        return accounts.containsKey(identifier);
    }

    @Override
    public void save(User user) {
        accounts.put(user.getUsername(), user);
        saveRemote(user);
    }

    private void saveRemote(User user) {
        MongoDatabase database = mongoClient.getDatabase("entities");
        MongoCollection<Document> patients = database.getCollection("patients");
        Document patient = new Document("username", user.getUsername())
                .append("password", user.getPassword());
        patients.insertOne(patient);
        System.out.println("Added patient to database");

    }
}