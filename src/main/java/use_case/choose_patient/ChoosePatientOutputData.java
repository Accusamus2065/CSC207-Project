package use_case.choose_patient;

public class ChoosePatientOutputData {

    private final String patient;
    private final boolean useCaseFailed;
    private final String username;


    public ChoosePatientOutputData(String username, String patient, boolean useCaseFailed) {
        this.username = username;
        this.patient = patient;
        this.useCaseFailed = useCaseFailed;
    }

    public String getPatient() {
        return patient;
    }

    public String getUsername() {
        return username;
    }

    public boolean isUseCaseFailed() {
        return useCaseFailed;
    }
}
