package entity.people;

import java.time.LocalDateTime;

public class Doctor implements IDoctor{
    private String username;
    private String password;
    private String specialty;
    private String degree;

    public Doctor(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    @Override
    public String getPassword() {
        return password;
    }

    public String getSpecialty() {
        return specialty;
    }

    public String getDegree() {
        return degree;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setDegree(String degree) {
        this.degree = degree;
    }

    public void setSpecialty(String specialty) {
        this.specialty = specialty;
    }
}
