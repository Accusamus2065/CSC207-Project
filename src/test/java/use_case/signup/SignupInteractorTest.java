package use_case.signup;

import data_access.InMemoryUserDataAccessObject;
import entity.people.DoctorUserFactory;
import entity.people.PatientUserFactory;
import org.junit.Test;

import static org.junit.Assert.*;
import static org.junit.Assert.assertEquals;

public class SignupInteractorTest {
    @Test
    public void signupPatientSuccessTest() {
        SignupUserDataAccessInterface userDAO = new InMemoryUserDataAccessObject();
        DoctorUserFactory docFactory = new DoctorUserFactory();
        PatientUserFactory patFactory = new PatientUserFactory();
        SignupOutputBoundary signupPresenter = new SignupOutputBoundary() {
            @Override
            public void prepareSuccessView(SignupOutputData user) {
                assertTrue(userDAO.existsByName(false, user.getUsername()));
                assertFalse(user.isUseCaseFailed());
            }

            @Override
            public void prepareFailView(String error) {
                fail("Test expected to pass.");
            }
        };
        SignupInteractor signupInteractor = new SignupInteractor(userDAO, signupPresenter, docFactory, patFactory);

        SignupInputData inputData = new SignupInputData("taml5", "Testing1", "Testing1", false);
        signupInteractor.execute(inputData);
    }

    @Test
    public void signupDoctorSuccessTest() {
        SignupUserDataAccessInterface userDAO = new InMemoryUserDataAccessObject();
        DoctorUserFactory docFactory = new DoctorUserFactory();
        PatientUserFactory patFactory = new PatientUserFactory();
        SignupOutputBoundary signupPresenter = new SignupOutputBoundary() {
            @Override
            public void prepareSuccessView(SignupOutputData user) {
                assertTrue(userDAO.existsByName(false, user.getUsername()));
                assertFalse(user.isUseCaseFailed());
            }

            @Override
            public void prepareFailView(String error) {
                fail("Test expected to pass.");
            }
        };
        SignupInteractor signupInteractor = new SignupInteractor(userDAO, signupPresenter, docFactory, patFactory);

        SignupInputData inputData = new SignupInputData("taml5", "Testing1", "Testing1", true);
        signupInteractor.execute(inputData);
    }

    @Test
    public void signupFailMatchingPasswordsTest() {
        SignupUserDataAccessInterface userDAO = new InMemoryUserDataAccessObject();
        DoctorUserFactory docFactory = new DoctorUserFactory();
        PatientUserFactory patFactory = new PatientUserFactory();
        SignupOutputBoundary signupPresenter = new SignupOutputBoundary() {
            @Override
            public void prepareSuccessView(SignupOutputData user) {
                fail("Test expected to fail since passwords don't match.");
            }

            @Override
            public void prepareFailView(String error) {
                assertEquals("Passwords don't match.", error);
                assertFalse(userDAO.existsByName(false, "taml5"));
            }
        };
        SignupInteractor signupInteractor = new SignupInteractor(userDAO, signupPresenter, docFactory, patFactory);

        SignupInputData signupInputData = new SignupInputData("taml5", "Abce45", "Abce5", false);
        signupInteractor.execute(signupInputData);
    }

    @Test
    public void signupFailUserExistingTest() {
        SignupUserDataAccessInterface userDAO = new InMemoryUserDataAccessObject();
        DoctorUserFactory docFactory = new DoctorUserFactory();
        PatientUserFactory patFactory = new PatientUserFactory();
        SignupOutputBoundary signupPresenter = new SignupOutputBoundary() {
            @Override
            public void prepareSuccessView(SignupOutputData user) {
                fail("Test expected to fail since user already exists.");
            }

            @Override
            public void prepareFailView(String error) {
                assertEquals("User already exists.", error);
            }
        };
        SignupInteractor signupInteractor = new SignupInteractor(userDAO, signupPresenter, docFactory, patFactory);
        userDAO.save(patFactory.create("taml5", "Abcedf123"));

        SignupInputData signupInputData = new SignupInputData("taml5", "Abce45", "Abce5", false);
        signupInteractor.execute(signupInputData);
    }

    @Test
    public void validUsernameTest() {
        SignupUserDataAccessInterface userDAO = new InMemoryUserDataAccessObject();
        SignupOutputBoundary signupPresenter = new SignupOutputBoundary() {
            @Override
            public void prepareSuccessView(SignupOutputData user) {
                assertTrue(userDAO.existsByName(false, "taml5"));
                assertNotNull(user);
                assertFalse(user.isUseCaseFailed());
                assertEquals(user.getUsername(), "taml5");
            }

            @Override
            public void prepareFailView(String error) {
                fail("Username expected to pass.");
            }
        };
        DoctorUserFactory docFactory = new DoctorUserFactory();
        PatientUserFactory patFactory = new PatientUserFactory();
        SignupInteractor signupInteractor = new SignupInteractor(userDAO, signupPresenter, docFactory, patFactory);

        SignupInputData inputData = new SignupInputData("taml5", "Abc123", "Abc123", false);
        signupInteractor.execute(inputData);
    }

    @Test
    public void invalidUsernameTest() {
        SignupUserDataAccessInterface userDAO = new InMemoryUserDataAccessObject();
        SignupOutputBoundary signupPresenter = new SignupOutputBoundary() {
            @Override
            public void prepareSuccessView(SignupOutputData user) {
                fail("Username expected to fail.");
            }

            @Override
            public void prepareFailView(String error) {
                assertEquals("Username is invalid.", error);
                assertFalse(userDAO.existsByName(false, "a"));
            }
        };
        DoctorUserFactory docFactory = new DoctorUserFactory();
        PatientUserFactory patFactory = new PatientUserFactory();
        SignupInteractor signupInteractor = new SignupInteractor(userDAO, signupPresenter, docFactory, patFactory);

        SignupInputData inputData = new SignupInputData("a", "Abc123", "Abc123", false);
        signupInteractor.execute(inputData);
    }

    @Test
    public void validPasswordTest() {
        SignupUserDataAccessInterface userDAO = new InMemoryUserDataAccessObject();
        SignupOutputBoundary signupPresenter = new SignupOutputBoundary() {
            @Override
            public void prepareSuccessView(SignupOutputData user) {
                assertTrue(userDAO.existsByName(false, "taml5"));
                assertNotNull(user);
                assertEquals(user.getUsername(), "taml5");
            }

            @Override
            public void prepareFailView(String error) {
                fail("Password expected to pass.");
            }
        };
        DoctorUserFactory docFactory = new DoctorUserFactory();
        PatientUserFactory patFactory = new PatientUserFactory();
        SignupInteractor signupInteractor = new SignupInteractor(userDAO, signupPresenter, docFactory, patFactory);

        SignupInputData inputData = new SignupInputData("taml5", "Abc123", "Abc123", false);
        signupInteractor.execute(inputData);
    }

    @Test
    public void invalidPasswordTest() {
        SignupUserDataAccessInterface userDAO = new InMemoryUserDataAccessObject();
        SignupOutputBoundary signupPresenter = new SignupOutputBoundary() {
            @Override
            public void prepareSuccessView(SignupOutputData user) {
                fail("Password expected to fail.");
            }

            @Override
            public void prepareFailView(String error) {
                assertEquals("Password requires a digit and a letter, be more than 5 characters, and cannot have any other characters.", error);
                assertFalse(userDAO.existsByName(false, "taml5"));
            }
        };
        DoctorUserFactory docFactory = new DoctorUserFactory();
        PatientUserFactory patFactory = new PatientUserFactory();
        SignupInteractor signupInteractor = new SignupInteractor(userDAO, signupPresenter, docFactory, patFactory);

        SignupInputData inputData = new SignupInputData("taml5", "A13", "A13", false);
        signupInteractor.execute(inputData);
    }
}
