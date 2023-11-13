package use_case.update.patient;

import entity.people.IPatient;

public interface PatientUpdateUserDataAccessInterface {
    void update(String oldUsername, IPatient user);

    boolean existsByName(boolean isDoctor, String identifier);
}
