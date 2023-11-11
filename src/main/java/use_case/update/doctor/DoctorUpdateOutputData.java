package use_case.update.doctor;

public class DoctorUpdateOutputData {
    private final String username;
    private final boolean useCaseFailed;

    public DoctorUpdateOutputData(String username, boolean useCaseFailed) {
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
