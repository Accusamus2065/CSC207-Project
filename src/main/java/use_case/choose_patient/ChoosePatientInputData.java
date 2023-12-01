package use_case.choose_patient;

public class ChoosePatientInputData {

    private final String username;
    private String patient;

    public ChoosePatientInputData(String username, String patient) {
        this.username = username;
        this.patient = patient;
    }

    String getUsername() {
        return username;
    }

    public String getPatient() {
        return patient;
    }
}
