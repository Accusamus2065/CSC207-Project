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
        IDoctor doctor = new CommonDoctor("testUsername", "password");
        doctorDAO.save(doctor);

        IDoctor getDoctor = doctorDAO.get("testUsername");
        assertNotNull(getDoctor);
        assertSame(doctor, getDoctor);

        Bson query = eq("username", "testUsername");
        doctors.deleteOne(query);
    }

    @Test
    public void getNullTest() {
        Bson query = eq("username", "testUsername");
        assertEquals(0, doctors.countDocuments(query));

        assertNull(doctorDAO.get("testUsername"));
    }

    @Test
    public void saveTest() {
        IDoctor doctor = (IDoctor) factory.create("testUsername",
                "password",
                "MD",
                "orthopedics");
        doctorDAO.save(doctor);

        assertTrue(doctorDAO.existsByName("testUsername"));

        Document query = new Document("username", "testUsername");
        assertEquals(1, doctors.countDocuments(query));
        Document DAOdoctor = doctors.find(query).first();

        assertNotNull(DAOdoctor);
        assertEquals("testUsername", DAOdoctor.getString("username"));
        assertEquals("password", DAOdoctor.getString("password"));
        assertEquals("MD", DAOdoctor.getString("degree"));
        assertEquals("orthopedics", DAOdoctor.getString("specialty"));
        doctors.deleteOne(query);
    }

    @Test
    public void updateTest() {
        IDoctor doctor = new CommonDoctor("testUsername", "password");
        doctorDAO.save(doctor);
        IDoctor updatedDoctor = (IDoctor) factory.create("testUsername1",
                "password1",
                "MD",
                "orthopedics");
        doctorDAO.update("testUsername", updatedDoctor);

        // Test if old username document has been deleted
        Document query = new Document("username", "testUsername");
        assertEquals(0, doctors.countDocuments(query));
        // Test if new username document in collection
        query = new Document("username", "testUsername1");
        assertEquals(1, doctors.countDocuments(query));
        // Test if new document has updated information
        Document DAOdoctor = doctors.find(query).first();

        assertNotNull(DAOdoctor);
        assertEquals(updatedDoctor.getUsername(), DAOdoctor.getString("username"));
        assertEquals(updatedDoctor.getPassword(), DAOdoctor.getString("password"));
        assertEquals(updatedDoctor.getDegree(), DAOdoctor.getString("degree"));
        assertEquals(updatedDoctor.getSpecialty(), DAOdoctor.getString("specialty"));

        doctors.deleteOne(query);
    }

    @Test
    public void existsByNameTest() {
        IDoctor doctor = new CommonDoctor("testUsername", "password");

        // test doctorDAO.existsByName works with doctorDAO.save
        doctorDAO.save(doctor);
        assertTrue(doctorDAO.existsByName("testUsername"));

        IDoctor updatedDoctor = (IDoctor) factory.create("testUsername1",
                "password1",
                "MD",
                "orthopedics");
        doctorDAO.update("testUsername", updatedDoctor);
        assertTrue(doctorDAO.existsByName("testUsername1"));

        Bson query = eq("username", "testUsername1");
        doctors.deleteOne(query);
    }

    @Test
    public void existsByNameFalseTest() {
        Bson query = eq("username", "testUsername");
        assertEquals(0, doctors.countDocuments(query));

        assertFalse(doctorDAO.existsByName("testUsername"));
    }
}