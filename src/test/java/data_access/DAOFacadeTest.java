package data_access;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import entity.mongo.MongoFactory;
import entity.people.CommonDoctor;
import entity.people.CommonPatient;
import entity.people.IDoctor;
import entity.people.IPatient;
import org.bson.Document;
import org.bson.conversions.Bson;
import org.junit.Test;

import static com.mongodb.client.model.Filters.eq;
import static org.junit.Assert.*;

public class DAOFacadeTest {
    DAOFacade daoFacade = new DAOFacade();
    private static final MongoClient mongoClient = MongoFactory.setUpMongoClient();
    MongoDatabase database = mongoClient.getDatabase("entities");
    MongoCollection<Document> doctors = database.getCollection("doctors");
    MongoCollection<Document> patients = database.getCollection("patients");

    @Test
    public void testPatientExistsByNameTrue() {
        IPatient patient = new CommonPatient("username", "password");
        daoFacade.save(patient);

        assertTrue(daoFacade.patientDAO.existsByName("username"));

        // Check if daoFacade matches patientDAO behaviour
        assertEquals(daoFacade.existsByName(false, "username"),
                daoFacade.patientDAO.existsByName("username"));

        Bson query = eq("username", "username");
        patients.deleteOne(query);
    }

    @Test
    public void testPatientExistsByNameFalse() {
        assertFalse(daoFacade.patientDAO.existsByName("username"));

        assertEquals(daoFacade.existsByName(false, "username"),
                daoFacade.patientDAO.existsByName("username"));
    }

    @Test
    public void testDoctorExistsByNameTrue() {
        IDoctor doctor = new CommonDoctor("username", "password");
        daoFacade.save(doctor);

        assertTrue(daoFacade.doctorDAO.existsByName("username"));

        // Check if daoFacade matches doctorDAO behaviour
        assertEquals(daoFacade.existsByName(true, "username"),
                daoFacade.doctorDAO.existsByName("username"));

        Bson query = eq("username", "username");
        doctors.deleteOne(query);
    }

    @Test
    public void testDoctorExistsByNameFalse() {
        assertFalse(daoFacade.doctorDAO.existsByName("username"));
        assertEquals(daoFacade.existsByName(true, "username"),
                daoFacade.doctorDAO.existsByName("username"));
    }

    @Test
    public void testGetPatient() {
        IPatient patient = new CommonPatient("username", "password");
        daoFacade.save(patient);

        IPatient getPatient = daoFacade.getPatient("username");
        assertNotNull(getPatient);
        assertEquals(patient, getPatient);

        Bson query = eq("username", "username");
        patients.deleteOne(query);
    }

    @Test
    public void testGetPatientNull() {
        assertNull(daoFacade.patientDAO.get("username"));

        assertSame(daoFacade.patientDAO.get("username"),
                daoFacade.getPatient("username"));
    }

    @Test
    public void testGetDoctor() {
        IDoctor doctor = new CommonDoctor("username", "password");
        daoFacade.save(doctor);

        IDoctor getDoctor = daoFacade.getDoctor("username");
        assertNotNull(getDoctor);
        assertEquals(doctor, getDoctor);

        Bson query = eq("username", "username");
        doctors.deleteOne(query);
    }

    @Test
    public void testUpdatePatient() {
        assertNull(daoFacade.patientDAO.get("username"));
        assertNull(daoFacade.getPatient("username"));
    }

    @Test
    public void testGetDoctorNull() {
        assertNull(daoFacade.doctorDAO.get("username"));
        assertNull(daoFacade.getDoctor("username"));
    }
}
