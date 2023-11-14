package data_access;

import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import entity.mongo.MongoFactory;
import entity.people.CommonDoctor;
import entity.people.DoctorUserFactory;
import entity.people.IDoctor;
import entity.people.User;
import org.bson.Document;
import org.bson.conversions.Bson;

import java.util.HashMap;
import java.util.Map;

import static com.mongodb.client.model.Filters.eq;

public class DoctorDAOImpl {
    private final Map<String, User> accounts = new HashMap<>();
    private static final MongoClient mongoClient = MongoFactory.setUpMongoClient();

    public DoctorDAOImpl(DoctorUserFactory doctorUserFactory) {
        MongoDatabase database = mongoClient.getDatabase("entities");
        MongoCollection<Document> collection = database.getCollection("doctors");
        FindIterable<Document> findIterable = collection.find();
        for (Document document : findIterable) {
            accounts.put(document.getString("username"),
                    doctorUserFactory.create(document.getString("username"),
                            document.getString("password"),
                            document.getString("specialty"),
                            document.getString("degree")));
        }
        for (Map.Entry<String, User> entry : accounts.entrySet()) {
            System.out.println("Key: " + entry.getKey() + ", Value: " + entry.getValue());
        }
    }

    public boolean existsByName(String identifier) {
        return accounts.containsKey(identifier);
    }

    public void save(User user) {
        accounts.put(user.getUsername(), user);
        save();
    }

    private void save() {
        MongoDatabase database = mongoClient.getDatabase("entities");
        MongoCollection<Document> patients = database.getCollection("doctors");
        for (User user : accounts.values()) {
            CommonDoctor commonDoctor = (CommonDoctor) user;
            Document document = new Document("username", commonDoctor.getUsername())
                    .append("password", commonDoctor.getPassword())
                    .append("specialty", commonDoctor.getSpecialty())
                    .append("degree", commonDoctor.getDegree());
            patients.insertOne(document);
        }
    }

    public IDoctor get(String username) {
        return (IDoctor) accounts.get(username);
    }

    public void update(String oldUsername, IDoctor user) {
        accounts.remove(oldUsername);
        accounts.put(user.getUsername(), user);
        updateDAO(oldUsername, user);
    }

    public void updateDAO(String oldUsername, IDoctor user) {
        MongoDatabase database = mongoClient.getDatabase("entities");
        MongoCollection<Document> doctors = database.getCollection("doctors");

        // this should return a single document since we assume oldUsername exists in the database as a username,
        // and usernames should be unique
        Bson query = eq("username", oldUsername);

        Document document = new Document("username", user.getUsername())
                .append("password", user.getPassword())
                .append("specialty", user.getSpecialty())
                .append("degree", user.getDegree());

        doctors.replaceOne(query, document);
    }
}