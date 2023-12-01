package interface_adapter.welcome.signup;

import org.junit.Test;
import use_case.welcome.signup.WelcomeSignupInputBoundary;

import static org.junit.Assert.*;

public class WelcomeSignupControllerTest {
    @Test
    public void executePatientTest() {
        WelcomeSignupInputBoundary signupInteractor = welcomeInputData -> {
            assertFalse(welcomeInputData.isDoctor());
        };
        WelcomeSignupController signupController = new WelcomeSignupController(signupInteractor);
        signupController.execute(false);
    }

    @Test
    public void executeDoctorTest() {
        WelcomeSignupInputBoundary signupInteractor = welcomeInputData -> {
            assertTrue(welcomeInputData.isDoctor());
        };
        WelcomeSignupController signupController = new WelcomeSignupController(signupInteractor);
        signupController.execute(true);
    }
}