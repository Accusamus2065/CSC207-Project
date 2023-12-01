package interface_adapter.welcome.login;

import org.junit.Test;
import use_case.welcome.login.WelcomeLoginInputBoundary;

import static org.junit.Assert.*;

public class WelcomeLoginControllerTest {
    @Test
    public void executePatientTest() {
        WelcomeLoginInputBoundary loginInputInteractor = welcomeInputData -> {
            assertFalse(welcomeInputData.isDoctor());
        };
        WelcomeLoginController welcomeLoginController = new WelcomeLoginController(loginInputInteractor);
        welcomeLoginController.execute(false);
    }

    @Test
    public void executeDoctorTest() {
        WelcomeLoginInputBoundary loginInputInteractor = welcomeInputData -> {
            assertTrue(welcomeInputData.isDoctor());
        };
        WelcomeLoginController welcomeLoginController = new WelcomeLoginController(loginInputInteractor);
        welcomeLoginController.execute(true);
    }
}