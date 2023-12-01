package interface_adapter.update.patient;

import org.junit.Test;
import use_case.update.patient.PatientUpdateInputBoundary;

import static org.junit.Assert.*;

public class PatientUpdateControllerTest {
    private static final String OLD_USERNAME = "PatUpdateTestOldUsername";
    private static final String NEW_USERNAME = "PatUpdateTestNewUsername";
    private static final String PASSWORD = "PatUpdateTestPassword";
    public static final String REPEAT_PASSWORD = "PatUpdateTestRepeatPassword";
    public static final String SEX = "PatUpdateTestSex";
    public static final String GENDER = "PatUpdateTestGender";
    public static final double HEIGHT = 1.7;
    public static final double WEIGHT = 55;
    public static final String BLOOD_TYPE = "O-";

    @Test
    public void executeTest() {
        PatientUpdateInputBoundary patientUpdateInteractor = patientUpdateInputData -> {
            assertEquals(OLD_USERNAME, patientUpdateInputData.getOldUsername());
            assertEquals(NEW_USERNAME, patientUpdateInputData.getNewUsername());
            assertEquals(PASSWORD, patientUpdateInputData.getPassword());
            assertEquals(REPEAT_PASSWORD, patientUpdateInputData.getRepeatPassword());
            assertEquals(SEX, patientUpdateInputData.getSex());
            assertEquals(GENDER, patientUpdateInputData.getGender());
            assertEquals(HEIGHT, patientUpdateInputData.getHeight(), 0.05);
            assertEquals(WEIGHT, patientUpdateInputData.getWeight(), 0.05);
            assertEquals(BLOOD_TYPE, patientUpdateInputData.getBloodType());
        };
        PatientUpdateController patientUpdateController = new PatientUpdateController(patientUpdateInteractor);
        patientUpdateController.execute(OLD_USERNAME,
                NEW_USERNAME,
                PASSWORD,
                REPEAT_PASSWORD,
                SEX,
                GENDER,
                HEIGHT,
                WEIGHT,
                BLOOD_TYPE);
    }
}