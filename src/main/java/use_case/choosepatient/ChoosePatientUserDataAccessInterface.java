package use_case.choosepatient;

import entity.people.IDoctor;
import entity.people.IPatient;
import entity.people.User;

import java.util.List;
import java.util.Map;

public interface ChoosePatientUserDataAccessInterface {
    boolean existsByName(boolean isDoctor, String identifier);

    User get(String username);

    IDoctor getDoctor(String username);

    IPatient getPatient(String username);

    Map<String, User> getAccountsPatient();
}
