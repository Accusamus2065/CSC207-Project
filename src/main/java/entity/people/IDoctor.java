package entity.people;

public interface IDoctor extends User{

    void setUsername(String username);
    void setPassword(String password);
    String getSpecialty();
    void setSpecialty(String specialty);
    String getDegree();
    void setDegree(String degree);
}
