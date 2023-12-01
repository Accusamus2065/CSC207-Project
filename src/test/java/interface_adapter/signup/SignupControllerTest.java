package interface_adapter.signup;

import org.junit.Test;
import use_case.signup.SignupInputBoundary;

import static org.junit.Assert.*;

public class SignupControllerTest {
    private static final String USERNAME = "SignupControllerTestUsername";
    private static final String PASSWORD = "SignupControllerTestPassword";
    private static final String REPEAT_PASSWORD = "SignupControllerTestRepeatPassword";

    @Test
    public void executePatientTest() {
        SignupInputBoundary signupInteractor = signupInputData -> {
            assertEquals(USERNAME, signupInputData.getUsername());
            assertEquals(PASSWORD, signupInputData.getPassword());
            assertEquals(REPEAT_PASSWORD, signupInputData.getRepeatPassword());
            assertFalse(signupInputData.isDoctor());
        };
        SignupController signupController = new SignupController(signupInteractor);
        signupController.execute(USERNAME, PASSWORD, REPEAT_PASSWORD, false);
    }

    @Test
    public void executeDoctorTest() {
        SignupInputBoundary signupInteractor = signupInputData -> {
            assertEquals(USERNAME, signupInputData.getUsername());
            assertEquals(PASSWORD, signupInputData.getPassword());
            assertEquals(REPEAT_PASSWORD, signupInputData.getRepeatPassword());
            assertTrue(signupInputData.isDoctor());
        };
        SignupController signupController = new SignupController(signupInteractor);
        signupController.execute(USERNAME, PASSWORD, REPEAT_PASSWORD, true);
    }
}