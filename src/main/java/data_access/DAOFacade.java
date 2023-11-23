package data_access;

import entity.chat.Conversation;
import entity.people.*;
import use_case.choosepatient.ChoosePatientUserDataAccessInterface;
import use_case.login.LoginUserDataAccessInterface;
import use_case.signup.SignupUserDataAccessInterface;
import use_case.update.doctor.DoctorUpdateUserDataAccessInterface;
import use_case.update.patient.PatientUpdateUserDataAccessInterface;

import java.util.List;
import java.util.Map;

public class DAOFacade implements
        SignupUserDataAccessInterface,
        LoginUserDataAccessInterface,
        DoctorUpdateUserDataAccessInterface,
        PatientUpdateUserDataAccessInterface,
        ChoosePatientUserDataAccessInterface {
    PatientDAOImpl patientDAO = new PatientDAOImpl(new PatientUserFactory());
    DoctorDAOImpl doctorDAO = new DoctorDAOImpl(new DoctorUserFactory());
    ConvoDAOImpl convoDAO = new ConvoDAOImpl();

    @Override
    public void save(IPatient user) {
        patientDAO.save(user);
    }

    @Override
    public void save(IDoctor user) {
        doctorDAO.save(user);
    }

    public void save(Conversation convo) {
        convoDAO.save(convo);
    }

    @Override
    public void update(String oldUsername, IDoctor user) {
        doctorDAO.update(oldUsername, user);
    }

    @Override
    public void update(String oldUsername, IPatient user) {
        patientDAO.update(oldUsername, user);
    }

    public boolean existsByName(boolean isDoctor, String identifier) {
        if (isDoctor) {
            return doctorDAO.existsByName(identifier);
        } else {
            return patientDAO.existsByName(identifier);
        }
    }

    @Override
    public IDoctor getDoctor(String username) {
        return doctorDAO.get(username);
    }

    @Override
    public IPatient getPatient(String username) {
        return patientDAO.get(username);
    }

    @Override
    public List<String> getPatientList() {
        return patientDAO.getPatientList();
    }
}
