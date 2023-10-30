package interface_adapter.welcome;

public class WelcomeState {
    private boolean isDoctor;

    public WelcomeState(WelcomeState copy) {
        isDoctor = copy.isDoctor;
    }

    public WelcomeState() { }

    public boolean isDoctor() {
        return isDoctor;
    }

    public void setDoctor(boolean doctor) {
        isDoctor = doctor;
    }
}
