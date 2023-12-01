package use_case.update.patient;

import data_access.InMemoryUserDataAccessObject;
import entity.people.IPatient;
import entity.people.PatientUserFactory;
import org.junit.Test;

import static org.junit.Assert.*;

public class PatientUpdateInteractorTest {
    private static final String USERNAME = "updateTestUsername";
    private static final String PASSWORD = "updateTestPassword";
    private final PatientUserFactory patientFactory = new PatientUserFactory();

    @Test
    public void successfulUpdateTest() {
        InMemoryUserDataAccessObject userDAO = new InMemoryUserDataAccessObject();
        userDAO.save(patientFactory.create(USERNAME, PASSWORD));

        PatientUpdateOutputBoundary patientUpdatePresenter = new PatientUpdateOutputBoundary() {
            @Override
            public void prepareSuccessView(PatientUpdateOutputData user) {
                assertFalse(user.isUseCaseFailed());
                assertEquals("newTestUsername", user.getUsername());

                assertFalse(userDAO.existsByName(false, USERNAME));
                assertTrue(userDAO.existsByName(false, "newTestUsername"));
                assertFalse(userDAO.existsByName(true, "newTestUsername"));

                IPatient newPatient = userDAO.getPatient("newTestUsername");
                assertEquals("newTestPassword123", newPatient.getPassword());
                assertEquals("O", newPatient.getSex());
                assertEquals("TestGender", newPatient.getGender());
                assertEquals(1.0, newPatient.getHeight(), 0.01);
                assertEquals(100.0, newPatient.getWeight(), 0.01);
                assertEquals("O+", newPatient.getBloodType());
            }

            @Override
            public void prepareFailView(String error) {
                fail("Use case not expected to fail.");
            }
        };

        PatientUpdateInteractor patientUpdateInteractor = new PatientUpdateInteractor(userDAO, patientUpdatePresenter, patientFactory);
        PatientUpdateInputData inputData = new PatientUpdateInputData(USERNAME,
                "newTestUsername",
                "newTestPassword123",
                "newTestPassword123",
                "O",
                "TestGender",
                1.0,
                100.0,
                "O+");

        patientUpdateInteractor.execute(inputData);
    }

    @Test
    public void badPasswordTest() {
        InMemoryUserDataAccessObject userDAO = new InMemoryUserDataAccessObject();
        userDAO.save(patientFactory.create(USERNAME, PASSWORD));

        PatientUpdateOutputBoundary patientUpdatePresenter = new PatientUpdateOutputBoundary() {
            @Override
            public void prepareSuccessView(PatientUpdateOutputData user) {
                fail("User case not expected to succeed.");
            }

            @Override
            public void prepareFailView(String error) {
                assertEquals("Password requires a digit and a letter, be more than 5 characters, and cannot have any other characters.", error);

                assertFalse(userDAO.existsByName(false, "newTestUsername"));
                assertTrue(userDAO.existsByName(false, USERNAME));

                assertEquals(PASSWORD, userDAO.getPatient(USERNAME).getPassword());
                assertNull(userDAO.getPatient(USERNAME).getSex());
                assertNull(userDAO.getPatient(USERNAME).getGender());
                assertEquals(0, userDAO.getPatient(USERNAME).getHeight(), 0.01);
                assertEquals(0, userDAO.getPatient(USERNAME).getWeight(), 0.01);
                assertNull(userDAO.getPatient(USERNAME).getBloodType());
            }
        };

        PatientUpdateInteractor patientUpdateInteractor = new PatientUpdateInteractor(userDAO, patientUpdatePresenter, patientFactory);
        PatientUpdateInputData inputData = new PatientUpdateInputData(USERNAME,
                "newTestUsername",
                "newTestPassword",
                "newTestPassword",
                "O",
                "TestGender",
                1.0,
                100.0,
                "O+");


        patientUpdateInteractor.execute(inputData);
    }

    @Test
    public void notMatchingPasswordsTest() {
        InMemoryUserDataAccessObject userDAO = new InMemoryUserDataAccessObject();
        userDAO.save(patientFactory.create(USERNAME, PASSWORD));

        PatientUpdateOutputBoundary patientUpdatePresenter = new PatientUpdateOutputBoundary() {
            @Override
            public void prepareSuccessView(PatientUpdateOutputData user) {
                fail("User case not expected to succeed.");
            }

            @Override
            public void prepareFailView(String error) {
                assertEquals("Passwords don't match.", error);

                assertFalse(userDAO.existsByName(false, "newTestUsername"));
                assertTrue(userDAO.existsByName(false, USERNAME));

                assertEquals(PASSWORD, userDAO.getPatient(USERNAME).getPassword());
                assertNull(userDAO.getPatient(USERNAME).getSex());
                assertNull(userDAO.getPatient(USERNAME).getGender());
                assertEquals(0, userDAO.getPatient(USERNAME).getHeight(), 0.01);
                assertEquals(0, userDAO.getPatient(USERNAME).getWeight(), 0.01);
                assertNull(userDAO.getPatient(USERNAME).getBloodType());
            }
        };

        PatientUpdateInteractor patientUpdateInteractor = new PatientUpdateInteractor(userDAO, patientUpdatePresenter, patientFactory);
        PatientUpdateInputData inputData = new PatientUpdateInputData(USERNAME,
                "newTestUsername",
                "newTestPassword123",
                "newTestPassword12345",
                "O",
                "TestGender",
                1.0,
                100.0,
                "O+");


        patientUpdateInteractor.execute(inputData);
    }

    @Test
    public void badUsernameTest() {
        InMemoryUserDataAccessObject userDAO = new InMemoryUserDataAccessObject();
        userDAO.save(patientFactory.create(USERNAME, PASSWORD));

        PatientUpdateOutputBoundary patientUpdatePresenter = new PatientUpdateOutputBoundary() {
            @Override
            public void prepareSuccessView(PatientUpdateOutputData user) {
                fail("User case not expected to succeed.");
            }

            @Override
            public void prepareFailView(String error) {
                assertEquals("Username is invalid.", error);

                assertFalse(userDAO.existsByName(false, "newTestUsername"));
                assertTrue(userDAO.existsByName(false, USERNAME));

                assertEquals(PASSWORD, userDAO.getPatient(USERNAME).getPassword());
                assertNull(userDAO.getPatient(USERNAME).getSex());
                assertNull(userDAO.getPatient(USERNAME).getGender());
                assertEquals(0, userDAO.getPatient(USERNAME).getHeight(), 0.01);
                assertEquals(0, userDAO.getPatient(USERNAME).getWeight(), 0.01);
                assertNull(userDAO.getPatient(USERNAME).getBloodType());
            }
        };

        PatientUpdateInteractor patientUpdateInteractor = new PatientUpdateInteractor(userDAO, patientUpdatePresenter, patientFactory);
        PatientUpdateInputData inputData = new PatientUpdateInputData(USERNAME,
                "a",
                "newTestPassword123",
                "newTestPassword123",
                "O",
                "TestGender",
                1.0,
                100.0,
                "O+");

        patientUpdateInteractor.execute(inputData);
    }

    @Test
    public void existingUsernameTest() {
        InMemoryUserDataAccessObject userDAO = new InMemoryUserDataAccessObject();
        userDAO.save(patientFactory.create(USERNAME, PASSWORD));
        userDAO.save(patientFactory.create("NewUsername", "NewPassword123"));

        PatientUpdateOutputBoundary patientUpdatePresenter = new PatientUpdateOutputBoundary() {
            @Override
            public void prepareSuccessView(PatientUpdateOutputData user) {
                fail("User case not expected to succeed.");
            }

            @Override
            public void prepareFailView(String error) {
                assertEquals("User already exists.", error);

                assertFalse(userDAO.existsByName(false, "newTestUsername"));
                assertTrue(userDAO.existsByName(false, USERNAME));

                assertEquals(PASSWORD, userDAO.getPatient(USERNAME).getPassword());
                assertNull(userDAO.getPatient(USERNAME).getSex());
                assertNull(userDAO.getPatient(USERNAME).getGender());
                assertEquals(0, userDAO.getPatient(USERNAME).getHeight(), 0.01);
                assertEquals(0, userDAO.getPatient(USERNAME).getWeight(), 0.01);
                assertNull(userDAO.getPatient(USERNAME).getBloodType());
            }
        };

        PatientUpdateInteractor patientUpdateInteractor = new PatientUpdateInteractor(userDAO, patientUpdatePresenter, patientFactory);
        PatientUpdateInputData inputData = new PatientUpdateInputData(USERNAME,
                "NewUsername",
                "newTestPassword123",
                "newTestPassword123",
                "O",
                "TestGender",
                1.0,
                100.0,
                "O+");


        patientUpdateInteractor.execute(inputData);
    }

    @Test
    public void badSexTest() {
        InMemoryUserDataAccessObject userDAO = new InMemoryUserDataAccessObject();
        userDAO.save(patientFactory.create(USERNAME, PASSWORD));

        PatientUpdateOutputBoundary patientUpdatePresenter = new PatientUpdateOutputBoundary() {
            @Override
            public void prepareSuccessView(PatientUpdateOutputData user) {
                fail("User case not expected to succeed.");
            }

            @Override
            public void prepareFailView(String error) {
                assertEquals("Sex must be either 'M', 'F' or 'O'.", error);

                assertFalse(userDAO.existsByName(false, "newTestUsername"));
                assertTrue(userDAO.existsByName(false, USERNAME));

                assertEquals(PASSWORD, userDAO.getPatient(USERNAME).getPassword());
                assertNull(userDAO.getPatient(USERNAME).getSex());
                assertNull(userDAO.getPatient(USERNAME).getGender());
                assertEquals(0, userDAO.getPatient(USERNAME).getHeight(), 0.01);
                assertEquals(0, userDAO.getPatient(USERNAME).getWeight(), 0.01);
                assertNull(userDAO.getPatient(USERNAME).getBloodType());
            }
        };

        PatientUpdateInteractor patientUpdateInteractor = new PatientUpdateInteractor(userDAO, patientUpdatePresenter, patientFactory);
        PatientUpdateInputData inputData = new PatientUpdateInputData(USERNAME,
                "newTestUsername",
                "newTestPassword123",
                "newTestPassword123",
                "Prefer not to say",
                "TestGender",
                1.0,
                100.0,
                "O+");

        patientUpdateInteractor.execute(inputData);
    }

    @Test
    public void badGenderTest() {
        InMemoryUserDataAccessObject userDAO = new InMemoryUserDataAccessObject();
        userDAO.save(patientFactory.create(USERNAME, PASSWORD));

        PatientUpdateOutputBoundary patientUpdatePresenter = new PatientUpdateOutputBoundary() {
            @Override
            public void prepareSuccessView(PatientUpdateOutputData user) {
                fail("User case not expected to succeed.");
            }

            @Override
            public void prepareFailView(String error) {
                assertEquals("Gender is Empty.", error);

                assertFalse(userDAO.existsByName(false, "newTestUsername"));
                assertTrue(userDAO.existsByName(false, USERNAME));

                assertEquals(PASSWORD, userDAO.getPatient(USERNAME).getPassword());
                assertNull(userDAO.getPatient(USERNAME).getSex());
                assertNull(userDAO.getPatient(USERNAME).getGender());
                assertEquals(0, userDAO.getPatient(USERNAME).getHeight(), 0.01);
                assertEquals(0, userDAO.getPatient(USERNAME).getWeight(), 0.01);
                assertNull(userDAO.getPatient(USERNAME).getBloodType());
            }
        };

        PatientUpdateInteractor patientUpdateInteractor = new PatientUpdateInteractor(userDAO, patientUpdatePresenter, patientFactory);
        PatientUpdateInputData inputData = new PatientUpdateInputData(USERNAME,
                "newTestUsername",
                "newTestPassword123",
                "newTestPassword123",
                "O",
                "",
                1.0,
                100.0,
                "O+");

        patientUpdateInteractor.execute(inputData);
    }

    @Test
    public void badHeightTest() {
        InMemoryUserDataAccessObject userDAO = new InMemoryUserDataAccessObject();
        userDAO.save(patientFactory.create(USERNAME, PASSWORD));

        PatientUpdateOutputBoundary patientUpdatePresenter = new PatientUpdateOutputBoundary() {
            @Override
            public void prepareSuccessView(PatientUpdateOutputData user) {
                fail("User case not expected to succeed.");
            }

            @Override
            public void prepareFailView(String error) {
                assertEquals("Height is invalid.", error);

                assertFalse(userDAO.existsByName(false, "newTestUsername"));
                assertTrue(userDAO.existsByName(false, USERNAME));

                assertEquals(PASSWORD, userDAO.getPatient(USERNAME).getPassword());
                assertNull(userDAO.getPatient(USERNAME).getSex());
                assertNull(userDAO.getPatient(USERNAME).getGender());
                assertEquals(0, userDAO.getPatient(USERNAME).getHeight(), 0.01);
                assertEquals(0, userDAO.getPatient(USERNAME).getWeight(), 0.01);
                assertNull(userDAO.getPatient(USERNAME).getBloodType());
            }
        };

        PatientUpdateInteractor patientUpdateInteractor = new PatientUpdateInteractor(userDAO, patientUpdatePresenter, patientFactory);
        PatientUpdateInputData inputData = new PatientUpdateInputData(USERNAME,
                "newTestUsername",
                "newTestPassword123",
                "newTestPassword123",
                "O",
                "TestGender",
                100.0,
                100.0,
                "O+");

        patientUpdateInteractor.execute(inputData);
    }

    @Test
    public void badWeightTest() {
        InMemoryUserDataAccessObject userDAO = new InMemoryUserDataAccessObject();
        userDAO.save(patientFactory.create(USERNAME, PASSWORD));

        PatientUpdateOutputBoundary patientUpdatePresenter = new PatientUpdateOutputBoundary() {
            @Override
            public void prepareSuccessView(PatientUpdateOutputData user) {
                fail("User case not expected to succeed.");
            }

            @Override
            public void prepareFailView(String error) {
                assertEquals("Weight is invalid.", error);

                assertFalse(userDAO.existsByName(false, "newTestUsername"));
                assertTrue(userDAO.existsByName(false, USERNAME));

                assertEquals(PASSWORD, userDAO.getPatient(USERNAME).getPassword());
                assertNull(userDAO.getPatient(USERNAME).getSex());
                assertNull(userDAO.getPatient(USERNAME).getGender());
                assertEquals(0, userDAO.getPatient(USERNAME).getHeight(), 0.01);
                assertEquals(0, userDAO.getPatient(USERNAME).getWeight(), 0.01);
                assertNull(userDAO.getPatient(USERNAME).getBloodType());
            }
        };

        PatientUpdateInteractor patientUpdateInteractor = new PatientUpdateInteractor(userDAO, patientUpdatePresenter, patientFactory);
        PatientUpdateInputData inputData = new PatientUpdateInputData(USERNAME,
                "newTestUsername",
                "newTestPassword123",
                "newTestPassword123",
                "O",
                "TestGender",
                1.0,
                1000.0,
                "O+");

        patientUpdateInteractor.execute(inputData);
    }

    @Test
    public void badBloodTypeTest() {
        InMemoryUserDataAccessObject userDAO = new InMemoryUserDataAccessObject();
        userDAO.save(patientFactory.create(USERNAME, PASSWORD));

        PatientUpdateOutputBoundary patientUpdatePresenter = new PatientUpdateOutputBoundary() {
            @Override
            public void prepareSuccessView(PatientUpdateOutputData user) {
                fail("User case not expected to succeed.");
            }

            @Override
            public void prepareFailView(String error) {
                assertEquals("Blood Type must be (A, B, AB, O with + or -).", error);

                assertFalse(userDAO.existsByName(false, "newTestUsername"));
                assertTrue(userDAO.existsByName(false, USERNAME));

                assertEquals(PASSWORD, userDAO.getPatient(USERNAME).getPassword());
                assertNull(userDAO.getPatient(USERNAME).getSex());
                assertNull(userDAO.getPatient(USERNAME).getGender());
                assertEquals(0, userDAO.getPatient(USERNAME).getHeight(), 0.01);
                assertEquals(0, userDAO.getPatient(USERNAME).getWeight(), 0.01);
                assertNull(userDAO.getPatient(USERNAME).getBloodType());
            }
        };

        PatientUpdateInteractor patientUpdateInteractor = new PatientUpdateInteractor(userDAO, patientUpdatePresenter, patientFactory);
        PatientUpdateInputData inputData = new PatientUpdateInputData(USERNAME,
                "newTestUsername",
                "newTestPassword123",
                "newTestPassword123",
                "O",
                "TestGender",
                1.0,
                100.0,
                "C++");

        patientUpdateInteractor.execute(inputData);
    }
}
