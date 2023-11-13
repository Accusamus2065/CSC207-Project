package data_access;


import entity.people.IDoctor;
import entity.people.IPatient;
import entity.people.User;
import interface_adapter.update.doctor.DoctorUpdateViewModel;
import use_case.signup.SignupUserDataAccessInterface;

import java.util.HashMap;
import java.util.Map;

public class InMemoryUserDataAccessObject implements SignupUserDataAccessInterface {

    private final Map<String, User> users = new HashMap<>();

    @Override
    public void save(IPatient user) {
    }

    @Override
    public void save(IDoctor user) {
        users.put(user.)
    }

    @Override
    public boolean existsByName(boolean isDoctor, String identifier) {
        return users.containsKey(identifier);
    }
}
