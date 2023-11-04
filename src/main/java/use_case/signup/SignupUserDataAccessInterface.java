package use_case.signup;


import entity.people.IDoctor;
import entity.people.IPatient;
import entity.people.User;

public interface SignupUserDataAccessInterface {
    boolean existsByName(boolean isDoctor, String identifier);

    IPatient save(IPatient patient);

    IDoctor save(IDoctor doctor);
}
