package data_access;


import entity.people.IDoctor;
import entity.people.IPatient;
import use_case.signup.SignupUserDataAccessInterface;

import java.util.HashMap;
import java.util.Map;

public class InMemoryUserDataAccessObject implements SignupUserDataAccessInterface {

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
    public boolean existsByName(boolean isDoctor, String identifier) {
        return doctors.containsKey(identifier) || patients.containsKey(identifier);
    }
}
