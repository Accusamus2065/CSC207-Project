package use_case.choosepatient;

public class ChoosePatientOutputData {

    private final String patient;
    private final boolean useCaseFailed;

    public ChoosePatientOutputData(String patient, boolean useCaseFailed) {
        this.patient = patient;
        this.useCaseFailed = useCaseFailed;
    }

    public String getPatient() {
        return patient;
    }

    public boolean isUseCaseFailed() {
        return useCaseFailed;
    }
}
