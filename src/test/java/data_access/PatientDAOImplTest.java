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
import static junit.framework.TestCase.*;

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
    public void testSave() {
        IPatient patient = new CommonPatient("testUsername", "password");
        patientDAO.save(patient);

        assertTrue(patientDAO.existsByName("testUsername"));

        Document query = new Document("username", "testUsername");
        assertEquals(1, patients.countDocuments(query));
        Document DAOpatient = patients.find(query).first();


        assertNotNull(DAOpatient);
        assertEquals("testUsername", DAOpatient.getString("username"));
        assertEquals("password", DAOpatient.getString("password"));
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
        Document DAOpatient = patients.find(query).first();

        assertNotNull(DAOpatient);
        assertEquals(updatedPatient.getUsername(), DAOpatient.getString("username"));
        assertEquals(updatedPatient.getPassword(), DAOpatient.getString("password"));
        assertEquals(updatedPatient.getSex(), DAOpatient.getString("sex"));
        assertEquals(updatedPatient.getGender(), DAOpatient.getString("gender"));
        assertEquals(updatedPatient.getHeight(), DAOpatient.getDouble("height"));
        assertEquals(updatedPatient.getWeight(), DAOpatient.getDouble("weight"));
        assertEquals(updatedPatient.getBloodType(), DAOpatient.getString("bloodtype"));

        patients.deleteOne(query);
    }
}
