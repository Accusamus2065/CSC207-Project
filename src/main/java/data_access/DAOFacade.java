package data_access;

import entity.people.*;
import use_case.login.LoginUserDataAccessInterface;
import use_case.signup.SignupUserDataAccessInterface;

public class DAOFacade implements
        SignupUserDataAccessInterface,
        LoginUserDataAccessInterface {
    PatientDAOImpl patientDAO = new PatientDAOImpl(new PatientUserFactory());
    DoctorDAOImpl doctorDAO = new DoctorDAOImpl(new DoctorUserFactory());

    @Override
    public void save(IPatient user) {
        patientDAO.save(user);
    }

    @Override
    public void save(IDoctor user) {
        doctorDAO.save(user);
    }

    public boolean existsByName(boolean isDoctor, String identifier) {
        if (isDoctor) {
            return doctorDAO.existsByName(identifier);
        } else {
            return patientDAO.existsByName(identifier);
        }
    }

    @Override
    public User get(String username) {
        return null;
    }

    @Override
    public IDoctor getDoctor(String username) {
        return doctorDAO.get(username);
    }

    @Override
    public IPatient getPatient(String username) {
        return patientDAO.get(username);
    }
}
