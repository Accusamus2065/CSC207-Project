package entity.people;

public class DoctorUserFactory {
    public IDoctor create(String username, String password) {
        return new CommonDoctor(username, password);
    }

    public IDoctor create(String username, String password, String degree, String specialty) {
        entity.people.CommonDoctor doc = (CommonDoctor) create(username, password);
        doc.setDegree(degree);
        doc.setSpecialty(specialty);
        return doc;
    }
}
