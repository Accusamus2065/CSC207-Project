package data_access;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import entity.mongo.MongoFactory;
import entity.people.CommonPatient;
import entity.people.IPatient;
import entity.people.PatientUserFactory;
import org.bson.Document;
import org.bson.conversions.Bson;
import org.junit.Test;

import static com.mongodb.client.model.Filters.eq;
import static org.junit.Assert.*;

public class PatientDAOImplTest {
    PatientDAOImpl patientDAO = new PatientDAOImpl(new PatientUserFactory());
    private static final MongoClient mongoClient = MongoFactory.setUpMongoClient();
    MongoDatabase database = mongoClient.getDatabase("entities");
    MongoCollection<Document> patients = database.getCollection("patients");

    @Test
    public void testExistsByName() {
        IPatient patient = new CommonPatient("testUsername", "password");
        PatientUserFactory factory = new PatientUserFactory();

        // Test patientDAO.existsByName works with patientDAO.save
        patientDAO.save(patient);
        assertTrue(patientDAO.existsByName("testUsername"));

        // Test patientDAO.existsByName works with patientDAO.update
        IPatient updatedPatient = (IPatient) factory.create("testUsername1",
                "password1",
                "male",
                "female",
                180,
                60,
                "O+");
        patientDAO.update("testUsername", updatedPatient);
        assertTrue(patientDAO.existsByName("testUsername1"));

        Bson query = eq("username", "testUsername1");
        patients.deleteOne(query);
    }

    @Test
    public void testGet() {
        IPatient patient = new CommonPatient("testUsername", "password");
        patientDAO.save(patient);

        IPatient getPatient = patientDAO.get("testUsername");
        assertNotNull(getPatient);
        assertSame(patient, getPatient);


        Bson query = eq("username", "testUsername");
        patients.deleteOne(query);
    }

    @Test
    public void getNullTest() {
        Bson query = eq("username", "testUsername");
        assertEquals(0, patients.countDocuments(query));

        assertNull(patientDAO.get("testUsername"));
    }

    @Test
    public void testSave() {
        IPatient patient = new CommonPatient("testUsername", "password");
        patientDAO.save(patient);

        assertTrue(patientDAO.existsByName("testUsername"));

        Document query = new Document("username", "testUsername");
        assertEquals(1, patients.countDocuments(query));
        Document DAOPatient = patients.find(query).first();


        assertNotNull(DAOPatient);
        assertEquals("testUsername", DAOPatient.getString("username"));
        assertEquals("password", DAOPatient.getString("password"));
        patients.deleteOne(query);
    }

    @Test
    public void testUpdate() {
        PatientUserFactory factory = new PatientUserFactory();

        IPatient patient = new CommonPatient("testUsername", "password");
        patientDAO.save(patient);
        IPatient updatedPatient = (IPatient) factory.create("testUsername1",
                "password1",
                "male",
                "female",
                180,
                60,
                "O+");
        patientDAO.update("testUsername", updatedPatient);

        // Test if old username document has been deleted
        Document query = new Document("username", "testUsername");
        assertEquals(0, patients.countDocuments(query));
        // Test if new username document in collection
        query = new Document("username", "testUsername1");
        assertEquals(1, patients.countDocuments(query));
        // Test if new document has updated information
        Document DAOPatient = patients.find(query).first();

        assertNotNull(DAOPatient);
        assertEquals(updatedPatient.getUsername(), DAOPatient.getString("username"));
        assertEquals(updatedPatient.getPassword(), DAOPatient.getString("password"));
        assertEquals(updatedPatient.getSex(), DAOPatient.getString("sex"));
        assertEquals(updatedPatient.getGender(), DAOPatient.getString("gender"));
        assertEquals(updatedPatient.getHeight(), DAOPatient.getDouble("height"), 0);
        assertEquals(updatedPatient.getWeight(), DAOPatient.getDouble("weight"), 0);
        assertEquals(updatedPatient.getBloodType(), DAOPatient.getString("bloodtype"));

        patients.deleteOne(query);
    }
}
