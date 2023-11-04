package use_case.signup;


import entity.people.IDoctor;
import entity.people.IPatient;
import entity.people.User;

public interface SignupUserDataAccessInterface {
    void save(IPatient user);
    void save(IDoctor user);
    void save(User user);
    boolean existsByName(boolean isDoctor, String identifier);
}
