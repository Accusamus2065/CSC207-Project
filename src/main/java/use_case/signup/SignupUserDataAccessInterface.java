package use_case.signup;


import entity.people.IDoctor;
import entity.people.IPatient;

public interface SignupUserDataAccessInterface {
    void save(IPatient user);
    void save(IDoctor user);
    boolean existsByName(boolean isDoctor, String identifier);
}
