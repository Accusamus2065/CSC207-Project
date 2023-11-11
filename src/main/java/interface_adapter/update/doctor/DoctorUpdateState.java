package interface_adapter.update.doctor;

public class DoctorUpdateState {
    private String username = "";
    private String usernameError = null;
    private String password = "";
    private String passwordError = null;
    private String repeatPassword = "";
    private String repeatPasswordError = null;
    private String specialty = "";
    private String specialtyError = null;
    private String degree = "";
    private String degreeError = null;
    private boolean isDoctor;

    public DoctorUpdateState() {
    }

    public DoctorUpdateState(DoctorUpdateState copy) {
        username = copy.username;
        usernameError = copy.usernameError;
        password = copy.password;
        passwordError = copy.passwordError;
        repeatPassword = copy.repeatPassword;
        repeatPasswordError = copy.repeatPasswordError;
        specialty = copy.specialty;
        specialtyError = copy.specialtyError;
        degree = copy.degree;
        degreeError = copy.degreeError;
        isDoctor = copy.isDoctor;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getUsernameError() {
        return usernameError;
    }

    public void setUsernameError(String usernameError) {
        this.usernameError = usernameError;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPasswordError() {
        return passwordError;
    }

    public void setPasswordError(String passwordError) {
        this.passwordError = passwordError;
    }

    public String getRepeatPassword() {
        return repeatPassword;
    }

    public void setRepeatPassword(String repeatPassword) {
        this.repeatPassword = repeatPassword;
    }

    public String getRepeatPasswordError() {
        return repeatPasswordError;
    }

    public void setRepeatPasswordError(String repeatPasswordError) {
        this.repeatPasswordError = repeatPasswordError;
    }

    public String getSpecialty() {
        return specialty;
    }

    public void setSpecialty(String specialty) {
        this.specialty = specialty;
    }

    public String getSpecialtyError() {
        return specialtyError;
    }

    public void setSpecialtyError(String specialtyError) {
        this.specialtyError = specialtyError;
    }

    public String getDegree() {
        return degree;
    }

    public void setDegree(String degree) {
        this.degree = degree;
    }

    public String getDegreeError() {
        return degreeError;
    }

    public void setDegreeError(String degreeError) {
        this.degreeError = degreeError;
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
                ", usernameError='" + usernameError + '\'' +
                ", password='" + password + '\'' +
                ", passwordError='" + passwordError + '\'' +
                ", repeatPassword='" + repeatPassword + '\'' +
                ", repeatPasswordError='" + repeatPasswordError + '\'' +
                ", specialty='" + specialty + '\'' +
                ", specialtyError='" + specialtyError + '\'' +
                ", degree='" + degree + '\'' +
                ", degreeError='" + degreeError + '\'' +
                ", isDoctor=" + isDoctor +
                '}';
    }
}
