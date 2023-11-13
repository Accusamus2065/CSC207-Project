package use_case.update.doctor;

import entity.people.IDoctor;

public interface DoctorUpdateUserDataAccessInterface {
    void update(String oldUsername, IDoctor user);

    boolean existsByName(boolean isDoctor, String identifier);
}
