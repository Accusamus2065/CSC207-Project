package use_case.choosepatient;

public class ChoosePatientInputData {

    private final String username;
    private final String password;

    private final Boolean isDoctor;

    public ChoosePatientInputData(String username, String password, Boolean isDoctor) {
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

    Boolean getIsDoctor() {
        return isDoctor;
    }

}
