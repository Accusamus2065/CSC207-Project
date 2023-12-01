package use_case.load_patient;

import data_access.InMemoryUserDataAccessObject;
import entity.people.CommonPatient;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

public class LoadPatientInteractorTest {

    @Test
    public void successLoadPatientTest() {
        // Arrange
        InMemoryUserDataAccessObject userDataAccessObject = new InMemoryUserDataAccessObject();
        userDataAccessObject.save(new CommonPatient("Patient1", "password1"));
        userDataAccessObject.save(new CommonPatient("Patient2", "password2"));

        LoadPatientInteractor loadPatientInteractor = new LoadPatientInteractor(userDataAccessObject);

        // Act
        List<String> patientList = loadPatientInteractor.execute();

        // Assert
        assertFalse(patientList.isEmpty());
        assertEquals(2, patientList.size());
        assertTrue(patientList.contains("Patient1"));
        assertTrue(patientList.contains("Patient2"));
    }

    @Test
    public void emptyPatientListTest() {
        // Arrange
        InMemoryUserDataAccessObject userDataAccessObject = new InMemoryUserDataAccessObject();
        LoadPatientInteractor loadPatientInteractor = new LoadPatientInteractor(userDataAccessObject);

        // Act
        List<String> patientList = loadPatientInteractor.execute();

        // Assert
        assertTrue(patientList.isEmpty());
    }

}
