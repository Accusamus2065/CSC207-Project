package use_case.choosepatient;

import org.junit.Test;

import static org.junit.Assert.*;

public class ChoosePatientInteractorTest {

    private static final String USERNAME = "TestUsername";
    private static final String PATIENT = "TestPatient";

    @Test
    public void successChoosePatientTest() {
        // Arrange
        ChoosePatientInputData input = new ChoosePatientInputData(USERNAME, PATIENT);
        ChoosePatientOutputBoundary choosePatientPresenter = new ChoosePatientOutputBoundary() {
            @Override
            public void prepareSuccessView(ChoosePatientOutputData patientData) {
                // Assert
                assertFalse(patientData.isUseCaseFailed());
                assertEquals(USERNAME, patientData.getUsername());
                assertEquals(PATIENT, patientData.getPatient());
            }

            @Override
            public void prepareFailView(String error) {
                // Fail the test if this branch is reached
                fail("Test not expected to fail.");
            }
        };

        ChoosePatientInteractor choosePatientInteractor = new ChoosePatientInteractor(choosePatientPresenter);
        choosePatientInteractor.execute(input);
    }

}
