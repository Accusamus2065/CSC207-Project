package use_case.welcome;

public class WelcomeOutputData {

    final private boolean isDoctor;

    public WelcomeOutputData(boolean isDoctor) {
        this.isDoctor = isDoctor;
    }

    public boolean isDoctor() {
        return isDoctor;
    }
}
