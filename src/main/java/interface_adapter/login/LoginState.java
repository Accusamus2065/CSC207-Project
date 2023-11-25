package interface_adapter.login;

public class LoginState {
    private String username = "";
    private String password = "";
    private String error = null;
    private boolean isDoctor;

    public LoginState(LoginState copy) {
        username = copy.username;
        password = copy.password;
        error = copy.error;
        isDoctor = copy.isDoctor;
    }

    // Because of the previous copy constructor, the default constructor must be explicit.
    public LoginState() {}

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
        return "LoginState{" +
                "username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", error='" + error + '\'' +
                ", isDoctor=" + isDoctor +
                '}';
    }
}
