package entity;

import java.time.LocalDateTime;

public class Doctor implements User{
    private String username;
    private String password;
    private String specialty;
    private String degree;

    private LocalDateTime ldt;

    public Doctor(String username, String password, LocalDateTime ldt) {
        this.username = username;
        this.password = password;
        this.ldt = ldt;
    }

    public String getUsername() {
        return username;
    }

    @Override
    public String getPassword() {
        return password;
    }

    public void setDegree(String degree) {
        this.degree = degree;
    }

    public void setSpecialty(String specialty) {
        this.specialty = specialty;
    }
}
