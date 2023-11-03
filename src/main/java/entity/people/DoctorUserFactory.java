package entity.people;

public class DoctorUserFactory implements UserFactory {

    @Override
    public User create(String username, String password) {
        return new Doctor(username, password);
    }

    public User create(String username, String password, String degree, String specialty) {
        Doctor doc = (Doctor) create(username, password);
        doc.setDegree(degree);
        doc.setSpecialty(specialty);
        return doc;
    }
}
