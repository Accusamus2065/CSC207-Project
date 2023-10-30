package entity.people;

import java.time.LocalDateTime;

public interface IDoctor extends User{
    String getUsername();

    String getPassword();
    String getSpecialty();
    String getDegree();

    void setUsername(String username);

    void setPassword(String password);

    void setDegree(String degree);

    void setSpecialty(String specialty);


}