package interface_adapter.choosepatient;

public class ChoosePatientState {
    private String username = "";
    private String usernameError = null;
    private String password = "";
    private String passwordError = null;
    private boolean isDoctor;

    public ChoosePatientState(ChoosePatientState copy) {
        username = copy.username;
        usernameError = copy.usernameError;
        password = copy.password;
        passwordError = copy.passwordError;
        isDoctor = copy.isDoctor;
    }

    // Because of the previous copy constructor, the default constructor must be explicit.
    public ChoosePatientState() {}

    public String getUsername() {
        return username;
    }

    public String getUsernameError() {
        return usernameError;
    }

    public String getPassword() {
        return password;
    }

    public String getPasswordError() {
        return passwordError;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setUsernameError(String usernameError) {
        this.usernameError = usernameError;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setPasswordError(String passwordError) {
        this.passwordError = passwordError;
    }

    public boolean isDoctor() {
        return isDoctor;
    }

    public void setDoctor(boolean doctor) {
        isDoctor = doctor;
    }

    @Override
    public String toString() {
        return "SignupState{" +
                "username='" + username + '\'' +
                ", password='" + password + '\'' +
                "isDoctor=" + isDoctor +
                '}';
    }
}