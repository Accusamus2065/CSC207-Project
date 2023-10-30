package entity.people;

import java.time.LocalDateTime;

public class DoctorUserFactory implements UserFactory {

    @Override
    public User create(String name, String password, LocalDateTime ldt) {
        return new Doctor(name, password, ldt);
    }

    public User create(String name, String password, LocalDateTime ldt, String degree, String specialty) {
        Doctor doc = (Doctor) create(name, password, ldt);
        doc.setDegree(degree);
        doc.setSpecialty(specialty);
        return doc;
    }

}
