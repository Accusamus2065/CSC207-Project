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
    private final DAOFacade daoFacade = new DAOFacade();
    private final MongoClient mongoClient = MongoFactory.setUpMongoClient();
    private final MongoDatabase database = mongoClient.getDatabase("entities");
    private final MongoCollection<Document> doctors = database.getCollection("doctors");
    private final MongoCollection<Document> patients = database.getCollection("patients");
    
    private static final String USERNAME = "testUsername";
    private static final String PASSWORD = "password";
    private static final String UPDATE_USERNAME = "testUpdatedUsername";
    private static final String UPDATE_PASSWORD = "testPassword";

    @Test
    public void testPatientExistsByNameTrue() {
        IPatient patient = new CommonPatient(USERNAME, PASSWORD);
        daoFacade.save(patient);

        assertTrue(daoFacade.patientDAO.existsByName(USERNAME));

        // Check if daoFacade matches patientDAO behaviour
        assertEquals(daoFacade.existsByName(false, USERNAME),
                daoFacade.patientDAO.existsByName(USERNAME));

        Bson query = eq("username", USERNAME);
        patients.deleteOne(query);
    }

    @Test
    public void testPatientExistsByNameFalse() {
        assertFalse(daoFacade.patientDAO.existsByName(USERNAME));

        assertEquals(daoFacade.existsByName(false, USERNAME),
                daoFacade.patientDAO.existsByName(USERNAME));
    }

    @Test
    public void testDoctorExistsByNameTrue() {
        IDoctor doctor = new CommonDoctor(USERNAME, PASSWORD);
        daoFacade.save(doctor);

        assertTrue(daoFacade.doctorDAO.existsByName(USERNAME));

        // Check if daoFacade matches doctorDAO behaviour
        assertEquals(daoFacade.existsByName(true, USERNAME),
                daoFacade.doctorDAO.existsByName(USERNAME));

        Bson query = eq("username", USERNAME);
        doctors.deleteOne(query);
    }

    @Test
    public void testDoctorExistsByNameFalse() {
        assertFalse(daoFacade.doctorDAO.existsByName(USERNAME));
        assertEquals(daoFacade.existsByName(true, USERNAME),
                daoFacade.doctorDAO.existsByName(USERNAME));
    }

    @Test
    public void testGetPatient() {
        IPatient patient = new CommonPatient(USERNAME, PASSWORD);
        daoFacade.save(patient);

        IPatient getPatient = daoFacade.getPatient(USERNAME);
        assertNotNull(getPatient);
        assertEquals(patient, getPatient);

        Bson query = eq("username", USERNAME);
        patients.deleteOne(query);
    }

    @Test
    public void testGetPatientNull() {
        assertNull(daoFacade.patientDAO.get(USERNAME));

        assertSame(daoFacade.patientDAO.get(USERNAME),
                daoFacade.getPatient(USERNAME));
    }

    @Test
    public void testGetDoctor() {
        IDoctor doctor = new CommonDoctor(USERNAME, PASSWORD);
        daoFacade.save(doctor);

        IDoctor getDoctor = daoFacade.getDoctor(USERNAME);
        assertNotNull(getDoctor);
        assertEquals(doctor, getDoctor);

        Bson query = eq("username", USERNAME);
        doctors.deleteOne(query);
    }

    @Test
    public void testUpdatePatient() {
        IPatient patient = new CommonPatient(USERNAME, PASSWORD);
        daoFacade.save(patient);

        IPatient updatedPatient = new CommonPatient(UPDATE_USERNAME, UPDATE_PASSWORD);
        daoFacade.update(USERNAME, updatedPatient);

        assertTrue(daoFacade.existsByName(false, UPDATE_USERNAME));
        assertFalse(daoFacade.existsByName(false, USERNAME));

        IPatient DAOPatient = daoFacade.getPatient(UPDATE_USERNAME);
        assertEquals(updatedPatient, DAOPatient);

        Bson query = eq("username", UPDATE_USERNAME);
        patients.deleteOne(query);
    }

    @Test
    public void testUpdateDoctor() {
        IDoctor doctor = new CommonDoctor(USERNAME, PASSWORD);
        daoFacade.save(doctor);

        IDoctor updatedDoctor = new CommonDoctor(UPDATE_USERNAME, UPDATE_PASSWORD);
        daoFacade.update(USERNAME, updatedDoctor);

        assertTrue(daoFacade.existsByName(true, UPDATE_USERNAME));
        assertFalse(daoFacade.existsByName(true, USERNAME));

        IDoctor DAODoctor = daoFacade.getDoctor(UPDATE_USERNAME);
        assertEquals(updatedDoctor, DAODoctor);

        Bson query = eq("username", UPDATE_USERNAME);
        doctors.deleteOne(query);
    }

    @Test
    public void testGetDoctorNull() {
        assertNull(daoFacade.doctorDAO.get(USERNAME));
        assertNull(daoFacade.getDoctor(USERNAME));
    }

    @Test
    public void testSavePatient() {
        IPatient patient = new CommonPatient(USERNAME, PASSWORD);
        daoFacade.save(patient);
        
        assertTrue(daoFacade.existsByName(false, USERNAME));
        assertTrue(daoFacade.patientDAO.existsByName(USERNAME));

        Bson query = eq("username", USERNAME);
        assertEquals(1, patients.countDocuments(query));

        patients.deleteOne(query);
    }

    @Test
    public void testSaveDoctor() {
        IDoctor doctor = new CommonDoctor(USERNAME, PASSWORD);
        daoFacade.save(doctor);

        assertTrue(daoFacade.existsByName(true, USERNAME));
        assertTrue(daoFacade.doctorDAO.existsByName(USERNAME));

        Bson query = eq("username", USERNAME);
        assertEquals(1, doctors.countDocuments(query));

        doctors.deleteOne(query);
    }
}
