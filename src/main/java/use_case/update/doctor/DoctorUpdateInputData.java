package use_case.update.doctor;

public class DoctorUpdateInputData {
    private final String oldUsername;
    private final String newUsername;
    private final String password;
    private final String repeatPassword;
    private final String specialty;
    private final String degree;

    public DoctorUpdateInputData(String oldUsername,
                                 String newUsername,
                                 String password,
                                 String repeatPassword,
                                 String specialty,
                                 String degree) {
        this.oldUsername = oldUsername;
        this.newUsername = newUsername;
        this.password = password;
        this.repeatPassword = repeatPassword;
        this.specialty = specialty;
        this.degree = degree;
    }

    public String getOldUsername() {
        return oldUsername;
    }

    public String getNewUsername() {
        return newUsername;
    }

    public String getPassword() {
        return password;
    }

    public String getRepeatPassword() {
        return repeatPassword;
    }

    public String getSpecialty() {
        return specialty;
    }

    public String getDegree() {
        return degree;
    }
}
