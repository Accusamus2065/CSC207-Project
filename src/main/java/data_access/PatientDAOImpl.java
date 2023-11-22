package data_access;


import com.mongodb.client.*;
import entity.mongo.MongoFactory;
import entity.people.IPatient;
import entity.people.PatientUserFactory;
import org.bson.Document;
import org.bson.conversions.Bson;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.mongodb.client.model.Filters.eq;

public class PatientDAOImpl {
    private final Map<String, IPatient> accounts = new HashMap<>();
    private static final MongoClient mongoClient = MongoFactory.setUpMongoClient();

    public PatientDAOImpl(PatientUserFactory patientUserFactory) {

        // Accessing the database
        MongoDatabase database = mongoClient.getDatabase("entities");

        // Accessing the collection
        MongoCollection<Document> collection = database.getCollection("patients");

        // Retrieving data from MongoDB and saving it to the Map object
        FindIterable<Document> findIterable = collection.find();
        for (Document document : findIterable) {
            //public User create(String name, String password, LocalDateTime ldt, char sex, String gender, float height, float weight, String bloodType)
            accounts.put(document.getString("username"),
                    patientUserFactory.create(document.getString("username"),
                            document.getString("password"),
                            document.getString("sex"),
                            document.getString("gender"),
                            (double) document.get("height"),
                            (double) document.get("weight"),
                            document.getString("bloodtype")));
        }

        // Printing the data stored in the Map object
        for (Map.Entry<String, IPatient> entry : accounts.entrySet()) {
            System.out.println("Key: " + entry.getKey() + ", Value: " + entry.getValue());
        }
    }

    public boolean existsByName(String identifier) {
        return accounts.containsKey(identifier);
    }

    public void save(IPatient user) {
        accounts.put(user.getUsername(), user);
        save();
    }

    private void save() {
        MongoDatabase database = mongoClient.getDatabase("entities");
        MongoCollection<Document> patients = database.getCollection("patients");
        for (IPatient patient : accounts.values()) {
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

    public IPatient get(String username) {
        return accounts.get(username);
    }

    public void update(String oldUsername, IPatient user) {
        accounts.remove(oldUsername);
        accounts.put(user.getUsername(), user);
        updateDAO(oldUsername, user);
    }

    public void updateDAO(String oldUsername, IPatient user) {
        MongoDatabase database = mongoClient.getDatabase("entities");
        MongoCollection<Document> patients = database.getCollection("patients");

        // this should return a single document since we assume oldUsername exists in the database as a username,
        // and usernames should be unique
        Bson query = eq("username", oldUsername);

        Document document = new Document("username", user.getUsername())
                .append("password", user.getPassword())
                .append("sex", user.getSex())
                .append("gender", user.getGender())
                .append("height", user.getHeight())
                .append("weight", user.getWeight())
                .append("bloodtype", user.getBloodType());

        patients.replaceOne(query, document);
    }

    public Map<String, IPatient> getAccounts() {
        return accounts;
    }

    public List<String> getPatientList() {
        return new ArrayList<>(accounts.keySet());
    }
}