package use_case.update.patient;

public class PatientUpdateOutputData {
    private final String username;
    private final boolean useCaseFailed;

    public PatientUpdateOutputData(String username, boolean useCaseFailed) {
        this.username = username;
        this.useCaseFailed = useCaseFailed;
    }

    public String getUsername() {
        return username;
    }

    public boolean isUseCaseFailed() {
        return useCaseFailed;
    }
}
