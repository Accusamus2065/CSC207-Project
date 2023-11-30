package interface_adapter.update.doctor;

public class DoctorUpdateState {
    private String username = "";
    private String newUsername = "";
    private String password = "";
    private String repeatPassword = "";
    private String specialty = "";
    private String degree = "";
    private String error = null;
    private boolean isDoctor;

    public DoctorUpdateState() {
    }

    public DoctorUpdateState(DoctorUpdateState copy) {
        username = copy.username;
        newUsername = copy.newUsername;
        password = copy.password;
        repeatPassword = copy.repeatPassword;
        specialty = copy.specialty;
        degree = copy.degree;
        error = copy.error;
        isDoctor = copy.isDoctor;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getNewUsername() {
        return newUsername;
    }

    public void setNewUsername(String newUsername) {
        this.newUsername = newUsername;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRepeatPassword() {
        return repeatPassword;
    }

    public void setRepeatPassword(String repeatPassword) {
        this.repeatPassword = repeatPassword;
    }

    public String getSpecialty() {
        return specialty;
    }

    public void setSpecialty(String specialty) {
        this.specialty = specialty;
    }

    public String getDegree() {
        return degree;
    }

    public void setDegree(String degree) {
        this.degree = degree;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public boolean isDoctor() {
        return isDoctor;
    }

    public void setDoctor(boolean doctor) {
        isDoctor = doctor;
    }

    @Override
    public String toString() {
        return "DoctorUpdateState{" +
                "username='" + username + '\'' +
                ", newUsername='" + newUsername + '\'' +
                ", password='" + password + '\'' +
                ", repeatPassword='" + repeatPassword + '\'' +
                ", specialty='" + specialty + '\'' +
                ", degree='" + degree + '\'' +
                ", error='" + error + '\'' +
                ", isDoctor=" + isDoctor +
                '}';
    }
}
