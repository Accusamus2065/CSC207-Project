package data_access;

import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import entity.mongo.MongoFactory;
import entity.people.Doctor;
import entity.people.DoctorUserFactory;
import entity.people.User;
import org.bson.Document;
import use_case.signup.SignupUserDataAccessInterface;

import java.util.HashMap;
import java.util.Map;

public class DoctorDAOImpl implements SignupUserDataAccessInterface {
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
        mongoClient.close();
    }

    @Override
    public boolean existsByName(String identifier) {
        return accounts.containsKey(identifier);
    }

    @Override
    public void save(User user) {
        accounts.put(user.getUsername(), user);
        save();
    }

    private void save() {
        MongoDatabase database = mongoClient.getDatabase("entities");
        MongoCollection<Document> patients = database.getCollection("doctors");
        for (User user : accounts.values()) {
            Doctor doctor = (Doctor) user;
            Document document = new Document("username", doctor.getUsername())
                    .append("password", doctor.getPassword())
                    .append("specialty", doctor.getSpecialty())
                    .append("degree", doctor.getDegree());
            patients.insertOne(document);
        }
    }
}