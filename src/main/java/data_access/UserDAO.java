package data_access;

import entity.people.User;

public interface UserDAO {
    void savePatient(User user);
    void saveDoctor(User user);
    boolean patientExistsByName(String identifier);
    boolean doctorExistsByName(String identifier);
}
