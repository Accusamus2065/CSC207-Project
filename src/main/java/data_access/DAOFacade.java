//package data_access;
//
//import entity.chat.Message;
//import entity.people.*;
//import use_case.chat.ConversationUserDataAccessInterface;
//import use_case.chatbot.DialogflowUserDataAccessInterface;
//import use_case.choose_patient.ChoosePatientUserDataAccessInterface;
//import use_case.login.LoginUserDataAccessInterface;
//import use_case.signup.SignupUserDataAccessInterface;
//import use_case.update.doctor.DoctorUpdateUserDataAccessInterface;
//import use_case.update.patient.PatientUpdateUserDataAccessInterface;
//
//import java.io.IOException;
//import java.util.List;
//
//public class DAOFacade implements
//        SignupUserDataAccessInterface,
//        LoginUserDataAccessInterface,
//        DoctorUpdateUserDataAccessInterface,
//        PatientUpdateUserDataAccessInterface,
//        ChoosePatientUserDataAccessInterface,
//        DialogflowUserDataAccessInterface,
//        ConversationUserDataAccessInterface {
//    PatientDAOImpl patientDAO = new PatientDAOImpl(new PatientUserFactory());
//    DoctorDAOImpl doctorDAO = new DoctorDAOImpl(new DoctorUserFactory());
//    ConvoDAOImpl convoDAO = new ConvoDAOImpl();
//    DialogflowDAOImpl dialogflowDAO = new DialogflowDAOImpl();
//    ConvoDAOImpl convoDAOImpl = new ConvoDAOImpl();
//
//    public DAOFacade() throws IOException {
//    }
//
//    @Override
//    public void save(IPatient user) {
//        patientDAO.save(user);
//    }
//
//    @Override
//    public void save(IDoctor user) {
//        doctorDAO.save(user);
//    }
//
//    public void save(Message msg) {
//        convoDAO.save(msg);
//    }
//
//    @Override
//    public List<Message> query(String user1, String user2) {
//        return convoDAOImpl.query(user1, user2);
//    }
//
//    @Override
//    public void deleteAll() {
//        convoDAOImpl.deleteAll();
//    }
//
//    @Override
//    public List<Message> query() {
//        return convoDAOImpl.query();
//    }
//
//    @Override
//    public void update(String oldUsername, IDoctor user) {
//        doctorDAO.update(oldUsername, user);
//    }
//
//    @Override
//    public void update(String oldUsername, IPatient user) {
//        patientDAO.update(oldUsername, user);
//    }
//
//    public boolean existsByName(boolean isDoctor, String identifier) {
//        if (isDoctor) {
//            return doctorDAO.existsByName(identifier);
//        } else {
//            return patientDAO.existsByName(identifier);
//        }
//    }
//
//    @Override
//    public IDoctor getDoctor(String username) {
//        return doctorDAO.get(username);
//    }
//
//    @Override
//    public IPatient getPatient(String username) {
//        return patientDAO.get(username);
//    }
//
//    @Override
//    public List<String> getPatientList() {
//        return patientDAO.getPatientList();
//    }
//
//    @Override
//    public List<Object> detectIntent(String userInput) {
//        return dialogflowDAO.detectIntent(userInput);
//    }   // TODO: change requried
//}

package data_access;

import entity.chat.Message;
import entity.people.*;
import use_case.chat.ConversationUserDataAccessInterface;
import use_case.chatbot.DialogflowUserDataAccessInterface;
import use_case.choose_patient.ChoosePatientUserDataAccessInterface;
import use_case.login.LoginUserDataAccessInterface;
import use_case.signup.SignupUserDataAccessInterface;
import use_case.update.doctor.DoctorUpdateUserDataAccessInterface;
import use_case.update.patient.PatientUpdateUserDataAccessInterface;

import java.io.IOException;
import java.util.List;

/**
 * DAOFacade serves as a facade for various data access operations
 * It implements multiple interfaces, providing a unified interface for different use cases.
 */
public class DAOFacade implements
        SignupUserDataAccessInterface,
        LoginUserDataAccessInterface,
        DoctorUpdateUserDataAccessInterface,
        PatientUpdateUserDataAccessInterface,
        ChoosePatientUserDataAccessInterface,
        DialogflowUserDataAccessInterface,
        ConversationUserDataAccessInterface {

    // Instances of DAO (Data Access Object) implementations
    PatientDAOImpl patientDAO = new PatientDAOImpl(new PatientUserFactory());
    DoctorDAOImpl doctorDAO = new DoctorDAOImpl(new DoctorUserFactory());
    DialogflowDAOImpl dialogflowDAO = new DialogflowDAOImpl();
    ConvoDAOImpl convoDAO = new ConvoDAOImpl();

    // Constructor for DAOFacade
    public DAOFacade() throws IOException {
        // Constructor logic (may initialize DAO instances)
    }

    // Interface method implementation to save a patient
    @Override
    public void save(IPatient user) {
        patientDAO.save(user);
    }

    // Interface method implementation to save a doctor
    @Override
    public void save(IDoctor user) {
        doctorDAO.save(user);
    }

    // Interface method implementation to save a message
    public void save(Message msg) {
        convoDAO.save(msg);
    }

    // Interface method implementation to query messages between two users
    @Override
    public List<Message> query(String user1, String user2) {
        return convoDAO.query(user1, user2);
    }

    // Interface method implementation to delete all messages
    @Override
    public void deleteAll() {
        convoDAO.deleteAll();
    }

    // Interface method implementation to query all messages
    @Override
    public List<Message> query() {
        return convoDAO.query();
    }

    // Interface method implementation to update a doctor
    @Override
    public void update(String oldUsername, IDoctor user) {
        doctorDAO.update(oldUsername, user);
    }

    // Interface method implementation to update a patient
    @Override
    public void update(String oldUsername, IPatient user) {
        patientDAO.update(oldUsername, user);
    }

    // Method to check if a user (doctor or patient) exists by name
    public boolean existsByName(boolean isDoctor, String identifier) {
        if (isDoctor) {
            return doctorDAO.existsByName(identifier);
        } else {
            return patientDAO.existsByName(identifier);
        }
    }

    // Interface method implementation to get a doctor by username
    @Override
    public IDoctor getDoctor(String username) {
        return doctorDAO.get(username);
    }

    // Interface method implementation to get a patient by username
    @Override
    public IPatient getPatient(String username) {
        return patientDAO.get(username);
    }

    // Interface method implementation to get a list of patients
    @Override
    public List<String> getPatientList() {
        return patientDAO.getPatientList();
    }

    // Interface method implementation to detect intent using Dialogflow
    @Override
    public List<Object> detectIntent(String userInput) {
        return dialogflowDAO.detectIntent(userInput);
    }   // TODO: change required
}
