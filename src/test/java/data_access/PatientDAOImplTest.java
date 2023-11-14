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
        IPatient patient = new CommonPatient("username", "password");
        PatientUserFactory factory = new PatientUserFactory();

        // Test patientDAO.existsByName works with patientDAO.save
        patientDAO.save(patient);
        assertTrue(patientDAO.existsByName("username"));

        // Test patientDAO.existsByName works with patientDAO.update
        IPatient updatedPatient = (IPatient) factory.create("username1",
                "password1",
                "male",
                "female",
                180,
                60,
                "O+");
        patientDAO.update("username", updatedPatient);
        assertTrue(patientDAO.existsByName("username1"));

        Bson query = eq("username", "username1");
        patients.deleteOne(query);
    }

    @Test
    public void testGet() {
        IPatient patient = new CommonPatient("username", "password");
        patientDAO.save(patient);
        IPatient getpatient = patientDAO.get("username");
        assert getpatient != null;
        assertEquals(patient, getpatient);


        Bson query = eq("username", "username");
        patients.deleteOne(query);
    }

    @Test
    public void testSave() {
        IPatient patient = new CommonPatient("username", "password");
        patientDAO.save(patient);

        assert patientDAO.existsByName("username");

        Document query = new Document("username", "username");
        assertEquals(1, patients.countDocuments(query));
        Document DAOpatient = patients.find(query).first();


        assert DAOpatient != null;
        assertEquals("username", DAOpatient.getString("username"));
        assertEquals("password", DAOpatient.getString("password"));
        patients.deleteOne(query);
    }

    @Test
    public void testUpdate() {
        PatientUserFactory factory = new PatientUserFactory();

        IPatient patient = new CommonPatient("username", "password");
        patientDAO.save(patient);
        IPatient updatedPatient = (IPatient) factory.create("username1",
                "password1",
                "male",
                "female",
                180,
                60,
                "O+");
        patientDAO.update("username", updatedPatient);

        // Test if old username document has been deleted
        Document query = new Document("username", "username");
        assertEquals(0, patients.countDocuments(query));
        // Test if new username document in collection
        query = new Document("username", "username1");
        assertEquals(1, patients.countDocuments(query));
        // Test if new document has updated information
        Document DAOpatient = patients.find(query).first();

        assert DAOpatient != null;
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
