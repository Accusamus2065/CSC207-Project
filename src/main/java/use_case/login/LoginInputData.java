package use_case.login;

public class LoginInputData {

    final private String username;
    final private String password;

    final private Boolean isDoctor;

    public LoginInputData(String username, String password, Boolean isDoctor) {
        this.username = username;
        this.password = password;
        this.isDoctor = isDoctor;
    }

    String getUsername() {
        return username;
    }

    String getPassword() {
        return password;
    }

    Boolean getIsDoctor(){return isDoctor; }

}
