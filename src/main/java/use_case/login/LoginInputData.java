package use_case.login;

public class LoginInputData {

    private final String username;
    private final String password;

    private final Boolean isDoctor;

    public LoginInputData(String username, String password, Boolean isDoctor) {
        this.username = username;
        this.password = password;
        this.isDoctor = isDoctor;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public Boolean isDoctor() {
        return isDoctor;
    }

}
