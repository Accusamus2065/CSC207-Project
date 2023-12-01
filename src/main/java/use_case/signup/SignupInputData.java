package use_case.signup;

public class SignupInputData {

    private final String username;
    private final String password;
    private final String repeatPassword;
    private final boolean isDoctor;

    public SignupInputData(String username, String password, String repeatPassword, boolean isDoctor) {
        this.username = username;
        this.password = password;
        this.repeatPassword = repeatPassword;
        this.isDoctor = isDoctor;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getRepeatPassword() {
        return repeatPassword;
    }

    public boolean isDoctor() {
        return isDoctor;
    }
}
