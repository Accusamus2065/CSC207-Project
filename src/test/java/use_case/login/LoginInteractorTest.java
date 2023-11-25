package use_case.login;

import data_access.InMemoryUserDataAccessObject;
import entity.people.DoctorUserFactory;
import use_case.signup.*;

import static org.junit.jupiter.api.Assertions.*;

public class LoginInteractorTest {
    public void successDoctorTest() {
        SignupInputData inputData = new SignupInputData(
                "doctor1", "password", "password", true);
        SignupUserDataAccessInterface userRepository = new InMemoryUserDataAccessObject();
    }
}
