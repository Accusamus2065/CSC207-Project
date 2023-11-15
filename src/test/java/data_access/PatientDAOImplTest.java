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
    private final PatientDAOImpl patientDAO = new PatientDAOImpl(new PatientUserFactory());
    private final MongoClient mongoClient = MongoFactory.setUpMongoClient();
    private final MongoDatabase database = mongoClient.getDatabase("entities");
    private final MongoCollection<Document> patients = database.getCollection("patients");
    private static final String USERNAME = "testUsername";
    private static final String PASSWORD = "password";
    private static final String UPDATE_USERNAME = "testUpdatedUsername";
    private static final String UPDATE_PASSWORD = "testPassword";

    @Test
    public void testExistsByName() {
        IPatient patient = new CommonPatient(USERNAME, PASSWORD);
        PatientUserFactory factory = new PatientUserFactory();

        // Test patientDAO.existsByName works with patientDAO.save
        patientDAO.save(patient);
        assertTrue(patientDAO.existsByName(USERNAME));

        // Test patientDAO.existsByName works with patientDAO.update
        IPatient updatedPatient = (IPatient) factory.create(UPDATE_USERNAME,
                UPDATE_PASSWORD,
                "male",
                "female",
                180,
                60,
                "O+");
        patientDAO.update(USERNAME, updatedPatient);
        assertTrue(patientDAO.existsByName(UPDATE_USERNAME));

        Bson query = eq("username", UPDATE_USERNAME);
        patients.deleteOne(query);
    }

    @Test
    public void testExistsByNameFalse() {
        Bson query = eq("username", USERNAME);
        assertEquals(0, patients.countDocuments(query));

        assertFalse(patientDAO.existsByName(USERNAME));
    }

    @Test
    public void testGet() {
        IPatient patient = new CommonPatient(USERNAME, PASSWORD);
        patientDAO.save(patient);

        IPatient getPatient = patientDAO.get(USERNAME);
        assertNotNull(getPatient);
        assertSame(patient, getPatient);


        Bson query = eq("username", USERNAME);
        patients.deleteOne(query);
    }

    @Test
    public void getNullTest() {
        Bson query = eq("username", USERNAME);
        assertEquals(0, patients.countDocuments(query));

        assertNull(patientDAO.get(USERNAME));
    }

    @Test
    public void testSave() {
        IPatient patient = new CommonPatient(USERNAME, PASSWORD);
        patientDAO.save(patient);

        assertTrue(patientDAO.existsByName(USERNAME));

        Document query = new Document("username", USERNAME);
        assertEquals(1, patients.countDocuments(query));
        Document DAOPatient = patients.find(query).first();


        assertNotNull(DAOPatient);
        assertEquals(USERNAME, DAOPatient.getString("username"));
        assertEquals(PASSWORD, DAOPatient.getString("password"));
        patients.deleteOne(query);
    }

    @Test
    public void testUpdate() {
        PatientUserFactory factory = new PatientUserFactory();

        IPatient patient = new CommonPatient(USERNAME, PASSWORD);
        patientDAO.save(patient);
        IPatient updatedPatient = (IPatient) factory.create(UPDATE_USERNAME,
                UPDATE_PASSWORD,
                "male",
                "female",
                180,
                60,
                "O+");
        patientDAO.update(USERNAME, updatedPatient);

        // Test if old username document has been deleted
        Document query = new Document("username", USERNAME);
        assertEquals(0, patients.countDocuments(query));
        // Test if new username document in collection
        query = new Document("username", UPDATE_USERNAME);
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
