package use_case.choosepatient;

import entity.people.IDoctor;
import entity.people.IPatient;
import entity.people.User;

import java.util.List;
import java.util.Map;

public interface ChoosePatientUserDataAccessInterface {
    boolean existsByName(boolean isDoctor, String identifier);

    IDoctor getDoctor(String username);

    IPatient getPatient(String username);

    List<String> getPatientList();
}
