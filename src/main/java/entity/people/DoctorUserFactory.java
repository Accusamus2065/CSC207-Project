package entity.people;

import java.time.LocalDateTime;

public class DoctorUserFactory implements UserFactory {

    @Override
    public User create(String name, String password) {
        return new Doctor(name, password);
    }

    public Doctor create(String name, String password, String degree, String specialty) {
        Doctor doc = (Doctor) create(name, password);
        doc.setDegree(degree);
        doc.setSpecialty(specialty);
        return doc;
    }

}
