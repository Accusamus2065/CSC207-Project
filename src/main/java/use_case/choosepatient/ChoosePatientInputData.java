package use_case.choosepatient;

public class ChoosePatientInputData {

    private final String username;
    private String patient = "";

    public ChoosePatientInputData(String username, String patient) {
        this.username = username;
        this.patient = patient;
    }

    public ChoosePatientInputData(String username) {
        this.username = username;
    }


    String getUsername() {
        return username;
    }

    public String getPatient() {
        return patient;
    }
}