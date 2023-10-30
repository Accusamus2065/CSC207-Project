package data_access;

import com.mongodb.client.*;
import entity.mongo.MongoFactory;
import entity.people.*;
import org.bson.Document;
import use_case.signup.SignupUserDataAccessInterface;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

public class DoctorDAOImpl implements SignupUserDataAccessInterface {
    private final Map<String, IDoctor> accounts = new HashMap<>();
    private static final MongoClient mongoClient = MongoFactory.setUpMongoClient();

    public DoctorDAOImpl(DoctorUserFactory doctorUserFactory) {

        // Accessing the database
        MongoDatabase database = mongoClient.getDatabase("entities");

        // Accessing the collection
        MongoCollection<Document> collection = database.getCollection("patient");

        // Retrieving data from MongoDB and saving it to the Map object
        FindIterable<Document> findIterable = collection.find();
        for (Document document : findIterable) {
            //public User create(String name, String password, LocalDateTime ldt, char sex, String gender, float height, float weight, String bloodType)
            accounts.put(document.getString("username"), doctorUserFactory.create(document.getString("name"),
                    document.getString("password"), document.getString("degree"), document.getString("specialty")));
        }

        // Printing the data stored in the Map object
        for (Map.Entry<String, IDoctor> entry : accounts.entrySet()) {
            System.out.println("Key: " + entry.getKey() + ", Value: " + entry.getValue());
        }

        // Closing the connection
        mongoClient.close();
    }

    @Override
    public boolean existsByName(String identifier) {
        return accounts.containsKey(identifier);
    }

    public void save(IDoctor doctor) {
        accounts.put(doctor.getUsername(), doctor);
        saveRemote(doctor);
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