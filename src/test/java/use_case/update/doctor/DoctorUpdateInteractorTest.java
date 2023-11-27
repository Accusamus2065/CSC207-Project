package use_case.update.doctor;

import data_access.InMemoryUserDataAccessObject;
import entity.people.DoctorUserFactory;
import entity.people.IDoctor;
import org.junit.Test;

import static org.junit.Assert.*;

public class DoctorUpdateInteractorTest {
    private static final String USERNAME = "updateTestUsername";
    private static final String PASSWORD = "updateTestPassword";
    private final DoctorUserFactory docFactory = new DoctorUserFactory();
    @Test
    public void successfulUpdateTest() {
        InMemoryUserDataAccessObject userDAO = new InMemoryUserDataAccessObject();
        userDAO.save(docFactory.create(USERNAME, PASSWORD));

        DoctorUpdateOutputBoundary doctorUpdatePresenter = new DoctorUpdateOutputBoundary() {
            @Override
            public void prepareSuccessView(DoctorUpdateOutputData user) {
                assertFalse(user.isUseCaseFailed());
                assertEquals("newTestUsername", user.getUsername());

                assertFalse(userDAO.existsByName(true, USERNAME));
                assertTrue(userDAO.existsByName(true, "newTestUsername"));
                assertFalse(userDAO.existsByName(false, "newTestUsername"));

                IDoctor newDoc = userDAO.getDoctor("newTestUsername");
                assertEquals("newTestPassword123", newDoc.getPassword());
                assertEquals("TestSpecialty", newDoc.getSpecialty());
                assertEquals("TestDegree", newDoc.getDegree());
            }

            @Override
            public void prepareFailView(String error) {
                fail("Use case not expected to fail.");
            }
        };
        DoctorUpdateInteractor doctorUpdateInteractor = new DoctorUpdateInteractor(userDAO, doctorUpdatePresenter, docFactory);
        DoctorUpdateInputData inputData = new DoctorUpdateInputData(USERNAME,
                "newTestUsername",
                "newTestPassword123",
                "newTestPassword123",
                "TestSpecialty",
                "TestDegree");

        doctorUpdateInteractor.execute(inputData);
    }

    @Test
    public void badPasswordTest() {
        InMemoryUserDataAccessObject userDAO = new InMemoryUserDataAccessObject();
        userDAO.save(docFactory.create(USERNAME, PASSWORD));

        DoctorUpdateOutputBoundary doctorUpdatePresenter = new DoctorUpdateOutputBoundary() {
            @Override
            public void prepareSuccessView(DoctorUpdateOutputData user) {
                fail("User case not expected to succeed.");
            }

            @Override
            public void prepareFailView(String error) {
                // check correct error has been raised
                assertEquals("Password requires a digit and a letter, be more than 5 characters, and cannot have any other characters.", error);

                assertFalse(userDAO.existsByName(true, "newTestUsername"));
                assertTrue(userDAO.existsByName(true, USERNAME));

                // check no details have been edited
                assertEquals(PASSWORD, userDAO.getDoctor(USERNAME).getPassword());
                assertNull(userDAO.getDoctor(USERNAME).getDegree());
                assertNull(userDAO.getDoctor(USERNAME).getSpecialty());
            }
        };

        DoctorUpdateInteractor doctorUpdateInteractor = new DoctorUpdateInteractor(userDAO, doctorUpdatePresenter, docFactory);
        DoctorUpdateInputData inputData = new DoctorUpdateInputData(USERNAME,
                "newTestUsername",
                "newTestPassword",
                "newTestPassword",
                "TestSpecialty",
                "TestDegree");

        doctorUpdateInteractor.execute(inputData);
    }

    @Test
    public void notMatchingPasswordsTest() {
        InMemoryUserDataAccessObject userDAO = new InMemoryUserDataAccessObject();
        userDAO.save(docFactory.create(USERNAME, PASSWORD));

        DoctorUpdateOutputBoundary doctorUpdatePresenter = new DoctorUpdateOutputBoundary() {
            @Override
            public void prepareSuccessView(DoctorUpdateOutputData user) {
                fail("User case not expected to succeed.");
            }

            @Override
            public void prepareFailView(String error) {
                // check correct error has been raised
                assertEquals("Passwords don't match.", error);

                assertFalse(userDAO.existsByName(true, "newTestUsername"));
                assertTrue(userDAO.existsByName(true, USERNAME));

                // check no details have been edited
                assertEquals(PASSWORD, userDAO.getDoctor(USERNAME).getPassword());
                assertNull(userDAO.getDoctor(USERNAME).getDegree());
                assertNull(userDAO.getDoctor(USERNAME).getSpecialty());
            }
        };

        DoctorUpdateInteractor doctorUpdateInteractor = new DoctorUpdateInteractor(userDAO, doctorUpdatePresenter, docFactory);
        DoctorUpdateInputData inputData = new DoctorUpdateInputData(USERNAME,
                "newTestUsername",
                "newTestPassword123",
                "newTestPassword12345",
                "TestSpecialty",
                "TestDegree");

        doctorUpdateInteractor.execute(inputData);
    }

    @Test
    public void badUsernameTest() {
        InMemoryUserDataAccessObject userDAO = new InMemoryUserDataAccessObject();
        userDAO.save(docFactory.create(USERNAME, PASSWORD));

        DoctorUpdateOutputBoundary doctorUpdatePresenter = new DoctorUpdateOutputBoundary() {
            @Override
            public void prepareSuccessView(DoctorUpdateOutputData user) {
                fail("User case not expected to succeed.");
            }

            @Override
            public void prepareFailView(String error) {
                // check correct error has been raised
                assertEquals("Username is invalid.", error);

                assertFalse(userDAO.existsByName(true, "newTestUsername"));
                assertTrue(userDAO.existsByName(true, USERNAME));

                // check no details have been edited
                assertEquals(PASSWORD, userDAO.getDoctor(USERNAME).getPassword());
                assertNull(userDAO.getDoctor(USERNAME).getDegree());
                assertNull(userDAO.getDoctor(USERNAME).getSpecialty());
            }
        };

        DoctorUpdateInteractor doctorUpdateInteractor = new DoctorUpdateInteractor(userDAO, doctorUpdatePresenter, docFactory);
        DoctorUpdateInputData inputData = new DoctorUpdateInputData(USERNAME,
                "a",
                "newTestPassword123",
                "newTestPassword123",
                "TestSpecialty",
                "TestDegree");

        doctorUpdateInteractor.execute(inputData);
    }

    @Test
    public void existingUsernameTest() {
        InMemoryUserDataAccessObject userDAO = new InMemoryUserDataAccessObject();
        userDAO.save(docFactory.create(USERNAME, PASSWORD));
        userDAO.save(docFactory.create("NewUsername", "NewPassword123"));

        DoctorUpdateOutputBoundary doctorUpdatePresenter = new DoctorUpdateOutputBoundary() {
            @Override
            public void prepareSuccessView(DoctorUpdateOutputData user) {
                fail("User case not expected to succeed.");
            }

            @Override
            public void prepareFailView(String error) {
                // check correct error has been raised
                assertEquals("User already exists.", error);

                assertFalse(userDAO.existsByName(true, "newTestUsername"));
                assertTrue(userDAO.existsByName(true, USERNAME));

                // check no details have been edited
                assertEquals(PASSWORD, userDAO.getDoctor(USERNAME).getPassword());
                assertNull(userDAO.getDoctor(USERNAME).getDegree());
                assertNull(userDAO.getDoctor(USERNAME).getSpecialty());
            }
        };

        DoctorUpdateInteractor doctorUpdateInteractor = new DoctorUpdateInteractor(userDAO, doctorUpdatePresenter, docFactory);
        DoctorUpdateInputData inputData = new DoctorUpdateInputData(USERNAME,
                "NewUsername",
                "NewPassword123",
                "NewPassword123",
                "TestSpecialty",
                "TestDegree");

        doctorUpdateInteractor.execute(inputData);
    }
}