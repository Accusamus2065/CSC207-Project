package entity.people;

public class DoctorUserFactory implements UserFactory {

    @Override
    public User create(String username, String password) {
        return new CommonDoctor(username, password);
    }

    public User create(String username, String password, String degree, String specialty) {
        CommonDoctor doc = (CommonDoctor) create(username, password);
        doc.setDegree(degree);
        doc.setSpecialty(specialty);
        return doc;
    }
}
