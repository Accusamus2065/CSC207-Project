package interface_adapter.signup;

public class SignupState {
    private String username = "";
    private String password = "";
    private String repeatPassword = "";
    private String error = null;
    private boolean isDoctor;

    public SignupState(SignupState copy) {
        username = copy.username;
        password = copy.password;
        repeatPassword = copy.repeatPassword;
        error = copy.error;
        isDoctor = copy.isDoctor;
    }

    public SignupState() {
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
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
        return "SignupState{" +
                "username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", repeatPassword='" + repeatPassword + '\'' +
                "isDoctor=" + isDoctor +
                '}';
    }
}
