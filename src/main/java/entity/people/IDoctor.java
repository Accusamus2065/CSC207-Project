package entity.people;

public interface IDoctor extends User {
    void setSpecialty(String specialty);

    void setDegree(String degree);

    String getUsername();

    String getPassword();

    String getSpecialty();

    String getDegree();
}
