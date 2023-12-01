package interface_adapter.update.doctor;

import org.junit.Test;
import use_case.update.doctor.DoctorUpdateInputBoundary;
import use_case.update.doctor.DoctorUpdateInputData;

import static org.junit.Assert.*;

public class DoctorUpdateControllerTest {
    private static final String OLD_USERNAME = "DocUpdateTestOldUsername";
    private static final String NEW_USERNAME = "DocUpdateTestNewUsername";
    private static final String PASSWORD = "DocUpdateTestPassword";
    public static final String REPEAT_PASSWORD = "DocUpdateTestRepeatPassword";
    public static final String SPECIALTY = "DocUpdateTestSpecialty";
    public static final String DEGREE = "DocUpdateTestDegree";
    @Test
    public void executeTest() {
        DoctorUpdateInputBoundary doctorUpdateInteractor = doctorUpdateInputData -> {
            assertEquals(OLD_USERNAME, doctorUpdateInputData.getOldUsername());
            assertEquals(NEW_USERNAME, doctorUpdateInputData.getNewUsername());
            assertEquals(PASSWORD, doctorUpdateInputData.getPassword());
            assertEquals(REPEAT_PASSWORD, doctorUpdateInputData.getRepeatPassword());
            assertEquals(SPECIALTY, doctorUpdateInputData.getSpecialty());
            assertEquals(DEGREE, doctorUpdateInputData.getDegree());
        };
        DoctorUpdateController doctorUpdateController = new DoctorUpdateController(doctorUpdateInteractor);
        doctorUpdateController.execute(OLD_USERNAME, NEW_USERNAME, PASSWORD, REPEAT_PASSWORD, SPECIALTY, DEGREE);
    }
}