package use_case.login;

import entity.people.IDoctor;
import entity.people.IPatient;

public interface LoginUserDataAccessInterface {
    boolean existsByName(boolean isDoctor, String identifier);

    IDoctor getDoctor(String username);

    IPatient getPatient(String username);
}
