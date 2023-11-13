package data_access;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import entity.mongo.MongoFactory;
import entity.people.CommonDoctor;
import entity.people.DoctorUserFactory;
import entity.people.IDoctor;
import org.bson.Document;
import org.bson.conversions.Bson;
import org.junit.Test;

import static com.mongodb.client.model.Filters.eq;
import static org.junit.Assert.*;

public class DoctorDAOImplTest {
    DoctorUserFactory factory = new DoctorUserFactory();
    DoctorDAOImpl doctorDAO = new DoctorDAOImpl(factory);
    private static final MongoClient mongoClient = MongoFactory.setUpMongoClient();
    MongoDatabase database = mongoClient.getDatabase("entities");
    MongoCollection<Document> doctors = database.getCollection("doctors");

    @Test
    public void getTest() {
        IDoctor doctor = new CommonDoctor("username", "password");
        doctorDAO.save(doctor);

        IDoctor getDoctor = doctorDAO.get("username");
        assert getDoctor != null;

        Bson query = eq("username", "username");
        doctors.deleteOne(query);
    }

    @Test
    public void saveTest() {
        IDoctor doctor = (IDoctor) factory.create("username",
                "password",
                "MD",
                "orthopedics");
        doctorDAO.save(doctor);

        assert doctorDAO.existsByName("username");

        Document query = new Document("username", "username");
        assertEquals(1, doctors.countDocuments(query));
        Document DAOdoctor = doctors.find(query).first();

        assert DAOdoctor != null;
        assertEquals("username", DAOdoctor.getString("username"));
        assertEquals("password", DAOdoctor.getString("password"));
        assertEquals("MD", DAOdoctor.getString("degree"));
        assertEquals("orthopedics", DAOdoctor.getString("specialty"));
        doctors.deleteOne(query);
    }

    @Test
    public void updateTest() {
        IDoctor doctor = new CommonDoctor("username", "password");
        doctorDAO.save(doctor);
        IDoctor updatedDoctor = (IDoctor) factory.create("username1",
                "password1",
                "MD",
                "orthopedics");
        doctorDAO.update("username", updatedDoctor);

        // Test if old username document has been deleted
        Document query = new Document("username", "username");
        assertEquals(0, doctors.countDocuments(query));
        // Test if new username document in collection
        query = new Document("username", "username1");
        assertEquals(1, doctors.countDocuments(query));
        // Test if new document has updated information
        Document DAOdoctor = doctors.find(query).first();

        assert DAOdoctor != null;
        assertEquals(updatedDoctor.getUsername(), DAOdoctor.getString("username"));
        assertEquals(updatedDoctor.getPassword(), DAOdoctor.getString("password"));
        assertEquals(updatedDoctor.getDegree(), DAOdoctor.getString("degree"));
        assertEquals(updatedDoctor.getSpecialty(), DAOdoctor.getString("specialty"));

        doctors.deleteOne(query);
    }

    @Test
    public void existsByNameTest() {
        IDoctor doctor = new CommonDoctor("username", "password");

        // test doctorDAO.existsByName works with doctorDAO.save
        doctorDAO.save(doctor);
        assertTrue(doctorDAO.existsByName("username"));

        IDoctor updatedDoctor = (IDoctor) factory.create("username1",
                "password1",
                "MD",
                "orthopedics");
        doctorDAO.update("username", updatedDoctor);
        assertTrue(doctorDAO.existsByName("username1"));

        Bson query = eq("username", "username1");
        doctors.deleteOne(query);
    }
}