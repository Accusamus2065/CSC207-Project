package use_case.signup;

public class SignupInputData {

    final private String username;
    final private String password;
    final private String repeatPassword;
    final private boolean isDoctor;

    public SignupInputData(String username, String password, String repeatPassword, boolean isDoctor) {
        this.username = username;
        this.password = password;
        this.repeatPassword = repeatPassword;
        this.isDoctor = isDoctor;
    }

    String getUsername() {
        return username;
    }

    String getPassword() {
        return password;
    }

    public String getRepeatPassword() {
        return repeatPassword;
    }

    public boolean isDoctor() {
        return isDoctor;
    }
}
