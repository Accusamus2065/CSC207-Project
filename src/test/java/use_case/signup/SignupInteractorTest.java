package use_case.signup;

import data_access.InMemoryUserDataAccessObject;
import entity.people.*;
import org.junit.Test;

import javax.print.Doc;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.fail;

public class SignupInteractorTest {
    @Test
    public void successDoctorTest(){
        SignupInputData inputData = new SignupInputData(
                "doctor1", "password", "password", true);
        SignupUserDataAccessInterface userRepository = new InMemoryUserDataAccessObject();

        SignupOutputBoundary successPresenter = new SignupOutputBoundary(){
            public void prepareSuccessView(SignupOutputData user){
                assertEquals("doctor1", user.getUsername());
                assertTrue(userRepository.existsByName(true, "doctor1"));
            }

            public void prepareFailView(String error){
                fail("User case failure is not expected");
            }

        };
        SignupInputBoundary interactor = new SignupInteractor(userRepository, successPresenter, new DoctorUserFactory(), new);
        interactor.execute(inputData);
    }

    @Test
    public void successPatientTest(){
        SignupInputData inputData = new SignupInputData(
                "patient1", "password", "password", false);
        SignupUserDataAccessInterface userRepository = new InMemoryUserDataAccessObject();

        SignupOutputBoundary successPresenter = new SignupOutputBoundary(){
            public void prepareSuccessView(SignupOutputData user){
                assertEquals("patient1", user.getUsername());
                assertTrue(userRepository.existsByName(false, "patient1"));
            }

            public void prepareFailView(String error){
                fail("User case failure is not expected");
            }

        };
        SignupInputBoundary interactor = new SignupInteractor(userRepository, successPresenter, new PatientUserFactory());
        interactor.execute(inputData);
    }

    @Test
    public void failurePasswordMismatchTestDoctor() {
        SignupInputData inputData = new SignupInputData(
                "doctor1", "password", "asl;dfjlkasdf", true);
        SignupUserDataAccessInterface userRepository = new InMemoryUserDataAccessObject();

        // This creates a presenter that tests whether the test case is as we expect.
        SignupOutputBoundary failurePresenter = new SignupOutputBoundary() {
            @Override
            public void prepareSuccessView(SignupOutputData user) {
                // 2 things to check: the output data is correct, and the user has been created in the DAO.
                fail("Use case success is unexpected.");
            }

            @Override
            public void prepareFailView(String error) {
                assertEquals("Passwords don't match.", error);
            }
        };

        SignupInputBoundary interactor = new
                SignupInteractor(userRepository, failurePresenter, new DoctorUserFactory(), new PatientUserFactory());
        interactor.execute(inputData);
    }

    @Test
    public void failurePasswordMismatchTestPatient() {
        SignupInputData inputData = new SignupInputData(
                "patient1", "password", "asl;dfjlkasdf", false);
        SignupUserDataAccessInterface userRepository = new InMemoryUserDataAccessObject();

        // This creates a presenter that tests whether the test case is as we expect.
        SignupOutputBoundary failurePresenter = new SignupOutputBoundary() {
            @Override
            public void prepareSuccessView(SignupOutputData user) {
                // 2 things to check: the output data is correct, and the user has been created in the DAO.
                fail("Use case success is unexpected.");
            }

            @Override
            public void prepareFailView(String error) {
                assertEquals("Passwords don't match.", error);
            }
        };

        SignupInputBoundary interactor = new
                SignupInteractor(userRepository, failurePresenter, new DoctorUserFactory(), new PatientUserFactory());
        interactor.execute(inputData);
    }

    @Test
    public void failureDoctorExistsTest() {
        SignupInputData inputData = new SignupInputData(
                "doctor1", "password", "password", true);
        SignupUserDataAccessInterface userRepository = new InMemoryUserDataAccessObject();

        // Add Paul to the repo so that when we check later they already exist
        DoctorUserFactory factory = new DoctorUserFactory();
        IDoctor doctor = (IDoctor) factory.create("doctor1", "ialreadyexist", "MedSchool", "Cancer");
        userRepository.save(doctor);

        // This creates a presenter that tests whether the test case is as we expect.
        SignupOutputBoundary failurePresenter = new SignupOutputBoundary() {
            @Override
            public void prepareSuccessView(SignupOutputData user) {
                // 2 things to check: the output data is correct, and the user has been created in the DAO.
                fail("Use case success is unexpected.");
            }

            @Override
            public void prepareFailView(String error) {
                assertEquals("User already exists.", error);
            }
        };

        SignupInputBoundary interactor = new
                SignupInteractor(userRepository, failurePresenter, new DoctorUserFactory(), new PatientUserFactory());
        interactor.execute(inputData);
    }

    @Test
    public void failurePatientExistsTest() {
        SignupInputData inputData = new SignupInputData(
                "patient1", "password", "password", false);
        SignupUserDataAccessInterface userRepository = new InMemoryUserDataAccessObject();

        // Add Paul to the repo so that when we check later they already exist
        PatientUserFactory factory = new PatientUserFactory();
        IPatient patient = (IPatient) factory.create("patient1", "ialreadyexist");
        userRepository.save(patient);

        // This creates a presenter that tests whether the test case is as we expect.
        SignupOutputBoundary failurePresenter = new SignupOutputBoundary() {
            @Override
            public void prepareSuccessView(SignupOutputData user) {
                // 2 things to check: the output data is correct, and the user has been created in the DAO.
                fail("Use case success is unexpected.");
            }

            @Override
            public void prepareFailView(String error) {
                assertEquals("User already exists.", error);
            }
        };

        SignupInputBoundary interactor = new
                SignupInteractor(userRepository, failurePresenter, new DoctorUserFactory(), new PatientUserFactory());
        interactor.execute(inputData);
    }

}
