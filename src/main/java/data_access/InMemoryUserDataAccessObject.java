package data_access;


import entity.people.IDoctor;
import entity.people.IPatient;
import entity.people.User;
import interface_adapter.update.doctor.DoctorUpdateViewModel;
import use_case.signup.SignupUserDataAccessInterface;

import java.util.HashMap;
import java.util.Map;

public class InMemoryUserDataAccessObject implements SignupUserDataAccessInterface {

    private final Map<String, User> doctors = new HashMap<>();
    private final Map<String, User> patients = new HashMap<>();


    @Override
    public void save(IPatient user) {patients.put(user.getUsername(), user);
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
