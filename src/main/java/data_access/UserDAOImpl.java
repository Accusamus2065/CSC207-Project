package data_access;

import entity.people.DoctorUserFactory;
import entity.people.PatientUserFactory;
import entity.people.User;

public class UserDAOImpl implements UserDAO {
    PatientDAOImpl patientDAO = new PatientDAOImpl(new PatientUserFactory());
    DoctorDAOImpl doctorDAO = new DoctorDAOImpl(new DoctorUserFactory());

    @Override
    public void savePatient(User user) {
        patientDAO.save(user);
    }

    @Override
    public void saveDoctor(User user) {
        doctorDAO.save(user);
    }

    @Override
    public boolean patientExistsByName(String identifier) {
        return patientDAO.existsByName(identifier);
    }

    @Override
    public boolean doctorExistsByName(String identifier) {
        return doctorDAO.existsByName(identifier);
    }
}
