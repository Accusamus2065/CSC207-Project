package interface_adapter.login;

import org.junit.Test;
import use_case.login.LoginInputBoundary;
import use_case.login.LoginInputData;

import static org.junit.Assert.*;

public class LoginControllerTest {
    private static final String USERNAME = "LoginControllerTestUsername";
    private static final String PASSWORD = "LoginControllerTestPassword";
    @Test
    public void executePatientTest() {
        LoginInputBoundary loginInteractor = loginInputData -> {
            assertEquals(USERNAME, loginInputData.getUsername());
            assertEquals(PASSWORD, loginInputData.getPassword());
            assertFalse(loginInputData.isDoctor());
        };
        LoginController loginController = new LoginController(loginInteractor);
        loginController.execute(USERNAME, PASSWORD, false);
    }

    @Test
    public void executeDoctorTest() {
        LoginInputBoundary loginInteractor = loginInputData -> {
            assertEquals(USERNAME, loginInputData.getUsername());
            assertEquals(PASSWORD, loginInputData.getPassword());
            assertTrue(loginInputData.isDoctor());
        };
        LoginController loginController = new LoginController(loginInteractor);
        loginController.execute(USERNAME, PASSWORD, true);
    }
}