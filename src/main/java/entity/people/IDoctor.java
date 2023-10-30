package entity.people;

import java.time.LocalDateTime;

public interface IDoctor {
    String getUsername();

    String getPassword();
    String getSpecialty();
    String getDegree();

    public void setUsername(String username);

    public void setPassword(String password);

    public void setDegree(String degree);

    public void setSpecialty(String specialty);
    }

}