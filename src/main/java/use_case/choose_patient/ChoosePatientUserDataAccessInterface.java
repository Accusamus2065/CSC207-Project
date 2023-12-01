package use_case.choose_patient;

import entity.people.IDoctor;
import entity.people.IPatient;

import java.util.List;

public interface ChoosePatientUserDataAccessInterface {
    boolean existsByName(boolean isDoctor, String identifier);

    IDoctor getDoctor(String username);

    IPatient getPatient(String username);

    List<String> getPatientList();
}
