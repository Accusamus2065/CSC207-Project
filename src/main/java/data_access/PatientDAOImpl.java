package data_access;

import com.mongodb.client.*;
import entity.mongo.MongoFactory;
import entity.people.Patient;
import entity.people.PatientUserFactory;
import entity.people.User;
import org.bson.Document;
import use_case.signup.SignupUserDataAccessInterface;

import java.util.HashMap;
import java.util.Map;

public class PatientDAOImpl implements SignupUserDataAccessInterface {
    private final Map<String, User> accounts = new HashMap<>();
    private static final MongoClient mongoClient = MongoFactory.setUpMongoClient();

    public PatientDAOImpl(PatientUserFactory patientUserFactory) {

        // Accessing the database
        MongoDatabase database = mongoClient.getDatabase("entities");

        // Accessing the collection
        MongoCollection<Document> collection = database.getCollection("patient");

        // Retrieving data from MongoDB and saving it to the Map object
        FindIterable<Document> findIterable = collection.find();
        for (Document document : findIterable) {
            //public User create(String name, String password, LocalDateTime ldt, char sex, String gender, float height, float weight, String bloodType)
            accounts.put(document.getString("username"), patientUserFactory.create(document.getString("name"),
                    document.getString("password"), (char) document.get("sex"),
                    document.getString("gender"), (float) document.get("height"),
                    (float) document.get("weight"), document.getString("bloodtype")));
        }

        // Printing the data stored in the Map object
        for (Map.Entry<String, User> entry : accounts.entrySet()) {
            System.out.println("Key: " + entry.getKey() + ", Value: " + entry.getValue());
        }

        // Closing the connection
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
        MongoCollection<Document> patients = database.getCollection("patients");
        for (User user : accounts.values()) {
            Patient patient = (Patient) user;
            Document document = new Document("username", patient.getUsername())
                    .append("password", patient.getPassword())
                    .append("sex", patient.getSex())
                    .append("gender", patient.getGender())
                    .append("height", patient.getHeight())
                    .append("weight", patient.getWeight())
                    .append("bloodtype", patient.getBloodType());
            patients.insertOne(document);
        }
    }
}