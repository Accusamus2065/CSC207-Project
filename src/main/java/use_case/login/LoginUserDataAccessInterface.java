package use_case.login;

import entity.people.IDoctor;
import entity.people.IPatient;
import entity.people.User;

public interface LoginUserDataAccessInterface {
    boolean existsByName(boolean isDoctor, String identifier);

    User get(String username);

    IDoctor getDoctor(String username);

    IPatient getPatient(String username);
}
