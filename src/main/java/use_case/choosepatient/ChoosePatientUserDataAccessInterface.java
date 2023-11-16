package use_case.choosepatient;

import entity.people.IDoctor;
import entity.people.IPatient;
import entity.people.User;

public interface ChoosePatientUserDataAccessInterface {
    boolean existsByName(boolean isDoctor, String identifier);

    User get(String username);

    IDoctor getDoctor(String username);

    IPatient getPatient(String username);
}
