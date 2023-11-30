package use_case.choosepatient;

public class ChoosePatientOutputData {

    private final String patient;
    private final String usecase;
    private final boolean useCaseFailed;
    private final String username;


    public ChoosePatientOutputData(String patient, String usecase, boolean useCaseFailed, String username) {
        this.patient = patient;
        this.usecase = usecase;
        this.useCaseFailed = useCaseFailed;
        this.username = username;
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
