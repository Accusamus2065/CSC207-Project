package use_case.choosepatient;

public class ChoosePatientOutputData {

    private final String username;
    private final boolean useCaseFailed;

    public ChoosePatientOutputData(String username, boolean useCaseFailed) {
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
