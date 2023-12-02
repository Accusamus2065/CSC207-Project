package data_access;


import entity.chat.Message;
import entity.people.IDoctor;
import entity.people.IPatient;
import use_case.chat.ConversationUserDataAccessInterface;
import use_case.chatbot.DialogflowUserDataAccessInterface;
import use_case.choose_patient.ChoosePatientUserDataAccessInterface;
import use_case.login.LoginUserDataAccessInterface;
import use_case.signup.SignupUserDataAccessInterface;
import use_case.update.doctor.DoctorUpdateUserDataAccessInterface;
import use_case.update.patient.PatientUpdateUserDataAccessInterface;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class InMemoryUserDataAccessObject implements SignupUserDataAccessInterface,
        LoginUserDataAccessInterface,
        DoctorUpdateUserDataAccessInterface,
        PatientUpdateUserDataAccessInterface,
        ChoosePatientUserDataAccessInterface,
        DialogflowUserDataAccessInterface,
        ConversationUserDataAccessInterface {

    private final Map<String, IDoctor> doctors = new HashMap<>();
    private final Map<String, IPatient> patients = new HashMap<>();


    @Override
    public void save(IPatient user) {
        patients.put(user.getUsername(), user);
    }

    @Override
    public void save(IDoctor user) {
        doctors.put(user.getUsername(), user);
    }

    @Override
    public void update(String oldUsername, IDoctor user) {
        doctors.remove(oldUsername);
        doctors.put(user.getUsername(), user);
    }

    @Override
    public void update(String oldUsername, IPatient user) {
        patients.remove(oldUsername);
        patients.put(user.getUsername(), user);
    }

    @Override
    public boolean existsByName(boolean isDoctor, String identifier) {
        if (isDoctor) {
            return doctors.containsKey(identifier);
        } else {
            return patients.containsKey(identifier);
        }
    }

    @Override
    public IDoctor getDoctor(String username) {
        return doctors.get(username);
    }

    @Override
    public IPatient getPatient(String username) {
        return patients.get(username);
    }

    @Override
    public List<String> getPatientList() {
        return new ArrayList<>(patients.keySet());
    }

    @Override
    public List<Object> detectIntent(String userInput) {
        return null;
    }   // TODO: needs to be changed

    @Override
    public void save(Message msg) {

    }

    @Override
    public List<Message> query(String user1, String user2) {
        return null;
    }

    @Override
    public void deleteAll() {

    }

    @Override
    public List<Message> query() {
        return null;
    }

}
