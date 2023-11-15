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
    private final DoctorUserFactory factory = new DoctorUserFactory();
    private final DoctorDAOImpl doctorDAO = new DoctorDAOImpl(factory);
    private final MongoClient mongoClient = MongoFactory.setUpMongoClient();
    private final MongoDatabase database = mongoClient.getDatabase("entities");
    private final MongoCollection<Document> doctors = database.getCollection("doctors");
    private static final String USERNAME = "testUsername";
    private static final String PASSWORD = "password";
    private static final String UPDATE_USERNAME = "testUpdatedUsername";
    private static final String UPDATE_PASSWORD = "testPassword";

    @Test
    public void getTest() {
        IDoctor doctor = new CommonDoctor(USERNAME, PASSWORD);
        doctorDAO.save(doctor);

        IDoctor getDoctor = doctorDAO.get(USERNAME);
        assertNotNull(getDoctor);
        assertSame(doctor, getDoctor);

        Bson query = eq("username", USERNAME);
        doctors.deleteOne(query);
    }

    @Test
    public void getNullTest() {
        Bson query = eq("username", USERNAME);
        assertEquals(0, doctors.countDocuments(query));

        assertNull(doctorDAO.get(USERNAME));
    }

    @Test
    public void saveTest() {
        IDoctor doctor = (IDoctor) factory.create(USERNAME,
                PASSWORD,
                "MD",
                "orthopedics");
        doctorDAO.save(doctor);

        assertTrue(doctorDAO.existsByName(USERNAME));

        Document query = new Document("username", USERNAME);
        assertEquals(1, doctors.countDocuments(query));
        Document DAODoctor = doctors.find(query).first();

        assertNotNull(DAODoctor);
        assertEquals(USERNAME, DAODoctor.getString("username"));
        assertEquals(PASSWORD, DAODoctor.getString("password"));
        assertEquals("MD", DAODoctor.getString("degree"));
        assertEquals("orthopedics", DAODoctor.getString("specialty"));
        doctors.deleteOne(query);
    }

    @Test
    public void updateTest() {
        IDoctor doctor = new CommonDoctor(USERNAME, PASSWORD);
        doctorDAO.save(doctor);
        IDoctor updatedDoctor = (IDoctor) factory.create(UPDATE_USERNAME,
                UPDATE_PASSWORD,
                "MD",
                "orthopedics");
        doctorDAO.update(USERNAME, updatedDoctor);

        // Test if old username document has been deleted
        Document query = new Document("username", USERNAME);
        assertEquals(0, doctors.countDocuments(query));
        // Test if new username document in collection
        query = new Document("username", UPDATE_USERNAME);
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
        IDoctor doctor = new CommonDoctor(USERNAME, PASSWORD);

        // test doctorDAO.existsByName works with doctorDAO.save
        doctorDAO.save(doctor);
        assertTrue(doctorDAO.existsByName(USERNAME));

        Bson query = eq("username", USERNAME);
        doctors.deleteOne(query);
    }

    @Test
    public void existsByNameFalseTest() {
        Bson query = eq("username", USERNAME);
        assertEquals(0, doctors.countDocuments(query));

        assertFalse(doctorDAO.existsByName(USERNAME));
    }
}