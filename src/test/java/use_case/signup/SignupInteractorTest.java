package use_case.signup;

import data_access.InMemoryUserDataAccessObject;
import entity.people.DoctorUserFactory;
import org.junit.Test;

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
                assertTrue(userRepository.existsByName(true, "tester"));
            }

            public void prepareFailView(String error){
                fail("User case failure is not expected");
            }

        };
        SignupInputBoundary interactor = new SignupInteractor(userRepository, successPresenter, new DoctorUserFactory());
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
                assertTrue(userRepository.existsByName(false, "tester"));
            }

            public void prepareFailView(String error){
                fail("User case failure is not expected");
            }

        };
        SignupInputBoundary interactor = new SignupInteractor(userRepository, successPresenter, new DoctorUserFactory());
        interactor.execute(inputData);
    }
}
