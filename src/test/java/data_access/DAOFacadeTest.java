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
        IPatient patient = new CommonPatient("testUsername", "password");
        daoFacade.save(patient);

        assertTrue(daoFacade.patientDAO.existsByName("testUsername"));

        // Check if daoFacade matches patientDAO behaviour
        assertEquals(daoFacade.existsByName(false, "testUsername"),
                daoFacade.patientDAO.existsByName("testUsername"));

        Bson query = eq("username", "testUsername");
        patients.deleteOne(query);
    }

    @Test
    public void testPatientExistsByNameFalse() {
        assertFalse(daoFacade.patientDAO.existsByName("testUsername"));

        assertEquals(daoFacade.existsByName(false, "testUsername"),
                daoFacade.patientDAO.existsByName("testUsername"));
    }

    @Test
    public void testDoctorExistsByNameTrue() {
        IDoctor doctor = new CommonDoctor("testUsername", "password");
        daoFacade.save(doctor);

        assertTrue(daoFacade.doctorDAO.existsByName("testUsername"));

        // Check if daoFacade matches doctorDAO behaviour
        assertEquals(daoFacade.existsByName(true, "testUsername"),
                daoFacade.doctorDAO.existsByName("testUsername"));

        Bson query = eq("username", "testUsername");
        doctors.deleteOne(query);
    }

    @Test
    public void testDoctorExistsByNameFalse() {
        assertFalse(daoFacade.doctorDAO.existsByName("testUsername"));
        assertEquals(daoFacade.existsByName(true, "testUsername"),
                daoFacade.doctorDAO.existsByName("testUsername"));
    }

    @Test
    public void testGetPatient() {
        IPatient patient = new CommonPatient("testUsername", "password");
        daoFacade.save(patient);

        IPatient getPatient = daoFacade.getPatient("testUsername");
        assertNotNull(getPatient);
        assertEquals(patient, getPatient);

        Bson query = eq("username", "testUsername");
        patients.deleteOne(query);
    }

    @Test
    public void testGetPatientNull() {
        assertNull(daoFacade.patientDAO.get("testUsername"));

        assertSame(daoFacade.patientDAO.get("testUsername"),
                daoFacade.getPatient("testUsername"));
    }

    @Test
    public void testGetDoctor() {
        IDoctor doctor = new CommonDoctor("testUsername", "password");
        daoFacade.save(doctor);

        IDoctor getDoctor = daoFacade.getDoctor("testUsername");
        assertNotNull(getDoctor);
        assertEquals(doctor, getDoctor);

        Bson query = eq("username", "testUsername");
        doctors.deleteOne(query);
    }

    @Test
    public void testUpdatePatient() {
        assertNull(daoFacade.patientDAO.get("testUsername"));
        assertNull(daoFacade.getPatient("testUsername"));
    }

    @Test
    public void testGetDoctorNull() {
        assertNull(daoFacade.doctorDAO.get("testUsername"));
        assertNull(daoFacade.getDoctor("testUsername"));
    }
}
