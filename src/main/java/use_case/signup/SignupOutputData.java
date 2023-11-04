package use_case.signup;

public class SignupOutputData {
    private final String username;
    private final boolean useCaseFailed;
    private final boolean isDoctor;

    public SignupOutputData(String username, boolean useCaseFailed, boolean isDoctor) {
        this.username = username;
        this.useCaseFailed = useCaseFailed;
        this.isDoctor = isDoctor;
    }

    public String getUsername() {
        return username;
    }
}
