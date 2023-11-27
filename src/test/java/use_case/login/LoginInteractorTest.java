package use_case.login;

import data_access.InMemoryUserDataAccessObject;
import entity.people.DoctorUserFactory;
import entity.people.PatientUserFactory;
import org.junit.Test;

import static org.junit.Assert.*;

public class LoginInteractorTest {
    private static final String USERNAME = "TestUsername";
    private static final String PASSWORD = "TestPassword123";
    private final DoctorUserFactory docFactory = new DoctorUserFactory();
    private final PatientUserFactory patFactory = new PatientUserFactory();

    @Test
    public void successDoctorTest() {
        LoginInputData inputData = new LoginInputData(
                USERNAME, PASSWORD, true);
        InMemoryUserDataAccessObject userDAO = new InMemoryUserDataAccessObject();
        userDAO.save(docFactory.create(USERNAME, PASSWORD));
        LoginOutputBoundary loginPresenter = new LoginOutputBoundary() {
            @Override
            public void prepareSuccessView(LoginOutputData user) {
                assertFalse(user.isUseCaseFailed());
                assertEquals(USERNAME, user.getUsername());
            }

            @Override
            public void prepareFailView(String error) {
                fail("Test not expected to fail.");
            }
        };
        LoginInteractor loginInteractor = new LoginInteractor(userDAO, loginPresenter);
        loginInteractor.execute(inputData);
    }

    @Test
    public void successPatientTest() {
        LoginInputData inputData = new LoginInputData(
                USERNAME, PASSWORD, false);
        InMemoryUserDataAccessObject userDAO = new InMemoryUserDataAccessObject();
        userDAO.save(patFactory.create(USERNAME, PASSWORD));
        LoginOutputBoundary loginPresenter = new LoginOutputBoundary() {
            @Override
            public void prepareSuccessView(LoginOutputData user) {
                assertFalse(user.isUseCaseFailed());
                assertEquals(USERNAME, user.getUsername());
            }

            @Override
            public void prepareFailView(String error) {
                fail("Test not expected to fail.");
            }
        };
        LoginInteractor loginInteractor = new LoginInteractor(userDAO, loginPresenter);
        loginInteractor.execute(inputData);
    }

    @Test
    public void noSuchDoctorFailureTest() {
        LoginInputData inputData = new LoginInputData(
                USERNAME, PASSWORD, true);
        InMemoryUserDataAccessObject userDAO = new InMemoryUserDataAccessObject();
        LoginOutputBoundary loginPresenter = new LoginOutputBoundary() {
            @Override
            public void prepareSuccessView(LoginOutputData user) {
                fail("Test not expected to succeed.");
            }

            @Override
            public void prepareFailView(String error) {
                assertEquals(USERNAME + ": Account does not exist.", error);
            }
        };
        LoginInteractor loginInteractor = new LoginInteractor(userDAO, loginPresenter);
        loginInteractor.execute(inputData);
    }

    @Test
    public void noSuchPatientFailureTest() {
        LoginInputData inputData = new LoginInputData(
                USERNAME, PASSWORD, false);
        InMemoryUserDataAccessObject userDAO = new InMemoryUserDataAccessObject();
        LoginOutputBoundary loginPresenter = new LoginOutputBoundary() {
            @Override
            public void prepareSuccessView(LoginOutputData user) {
                fail("Test not expected to succeed.");
            }

            @Override
            public void prepareFailView(String error) {
                assertEquals(USERNAME + ": Account does not exist.", error);
            }
        };
        LoginInteractor loginInteractor = new LoginInteractor(userDAO, loginPresenter);
        loginInteractor.execute(inputData);
    }

    @Test
    public void wrongPasswordDoctorTest() {
        LoginInputData inputData = new LoginInputData(
                USERNAME, PASSWORD, true);
        InMemoryUserDataAccessObject userDAO = new InMemoryUserDataAccessObject();
        userDAO.save(docFactory.create(USERNAME, "TestDifferentPassword123"));
        LoginOutputBoundary loginPresenter = new LoginOutputBoundary() {
            @Override
            public void prepareSuccessView(LoginOutputData user) {
                fail("Test not expected to succeed.");
            }

            @Override
            public void prepareFailView(String error) {
                assertEquals("Incorrect password for " + USERNAME + ".", error);
            }
        };
        LoginInteractor loginInteractor = new LoginInteractor(userDAO, loginPresenter);
        loginInteractor.execute(inputData);
    }

    @Test
    public void wrongPasswordPatientTest() {
        LoginInputData inputData = new LoginInputData(
                USERNAME, PASSWORD, true);
        InMemoryUserDataAccessObject userDAO = new InMemoryUserDataAccessObject();
        userDAO.save(docFactory.create(USERNAME, "TestDifferentPassword123"));
        LoginOutputBoundary loginPresenter = new LoginOutputBoundary() {
            @Override
            public void prepareSuccessView(LoginOutputData user) {
                fail("Test not expected to succeed.");
            }

            @Override
            public void prepareFailView(String error) {
                assertEquals("Incorrect password for " + USERNAME + ".", error);
            }
        };
        LoginInteractor loginInteractor = new LoginInteractor(userDAO, loginPresenter);
        loginInteractor.execute(inputData);
    }
}
