package use_case.welcome;

public class WelcomeInputData {

    final private boolean isDoctor;

    public WelcomeInputData(boolean isDoctor) {
        this.isDoctor = isDoctor;
    }

    public boolean isDoctor() {
        return isDoctor;
    }
}
