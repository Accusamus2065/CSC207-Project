package interface_adapter.choose_patient;

import org.junit.Test;
import use_case.choose_patient.ChoosePatientInputBoundary;
import use_case.choose_patient.ChoosePatientInputData;

import static org.junit.Assert.*;

public class ChoosePatientControllerTest {
    private static final String USERNAME = "ChoosePatientTestUsername";
    private static final String PATIENT_NAME = "Patient1";

    @Test
    public void executeTest() {
        ChoosePatientInputBoundary interactor = choosePatientInputData -> {
            assertEquals(PATIENT_NAME, choosePatientInputData.getPatient());
            assertEquals(USERNAME, choosePatientInputData.getUsername());
        };
        ChoosePatientController patientController = new ChoosePatientController(interactor);
        patientController.execute(USERNAME, PATIENT_NAME);
    }
}