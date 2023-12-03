package data_access;

import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

import entity.mongo.MongoFactory;
import entity.people.DoctorUserFactory;
import entity.people.IDoctor;
import org.bson.Document;
import org.bson.conversions.Bson;
import use_case.signup.SignupUserDataAccessInterface;

import java.util.*;

import static com.mongodb.client.model.Filters.eq;

public class DoctorDAOImpl {
    private final Map<String, IDoctor> accounts = new HashMap<>();
    private static final MongoClient mongoClient = MongoFactory.setUpMongoClient();
    private final String databaseName;

    public DoctorDAOImpl(DoctorUserFactory doctorUserFactory, String databaseName) {
        this.databaseName = databaseName;
        MongoDatabase database = mongoClient.getDatabase(databaseName);
        MongoCollection<Document> collection = database.getCollection("doctors");
        FindIterable<Document> findIterable = collection.find();
        for (Document document : findIterable) {
            accounts.put(document.getString("username"),
                    doctorUserFactory.create(document.getString("username"),
                            document.getString("password"),
                            document.getString("specialty"),
                            document.getString("degree")));
        }

        for (Map.Entry<String, IDoctor> entry : accounts.entrySet()) {
            System.out.println("Key: " + entry.getKey() + ", Value: " + entry.getValue());
        }
    }

    public boolean existsByName(String identifier) {
        return accounts.containsKey(identifier);
    }

    public void save(IDoctor user) {
        accounts.put(user.getUsername(), user);
        save();
    }

    private void save() {
        MongoDatabase database = mongoClient.getDatabase(databaseName);
        MongoCollection<Document> patients = database.getCollection("doctors");
        for (IDoctor doctor : accounts.values()) {
            Document document = new Document("username", doctor.getUsername())
                    .append("password", doctor.getPassword())
                    .append("specialty", doctor.getSpecialty())
                    .append("degree", doctor.getDegree());
            patients.insertOne(document);
        }
    }

    public IDoctor get(String username) {
        return accounts.get(username);
    }


    public List<String> getBySpecialty(String intent) {

        MongoDatabase database = mongoClient.getDatabase(databaseName);
        MongoCollection<Document> collection = database.getCollection("doctors");
        FindIterable<Document> findIterable = collection.find(new Document("specialty", intent));
        List list = new ArrayList();
        for (Document document : findIterable) {
            list.add(document.getString("username"));
        }
        return list;
    }

    public void update(String oldUsername, IDoctor user) {
        accounts.remove(oldUsername);
        accounts.put(user.getUsername(), user);
        updateDAO(oldUsername, user);
    }

    public void updateDAO(String oldUsername, IDoctor user) {
        MongoDatabase database = mongoClient.getDatabase(databaseName);
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