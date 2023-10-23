package interface_adapter.welcome;

import interface_adapter.ViewModel;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class WelcomeViewModel extends ViewModel {
    public static final String TITLE_LABEL = "Welcome!";
    public static final String SIGNUP_BUTTON_LABEL = "Sign Up";
    public static final String LOGIN_BUTTON_LABEL = "Log In";

    public static final String DOCTOR_CHECKBOX_LABEL = "I'm a doctor";
    private final PropertyChangeSupport support = new PropertyChangeSupport(this);

    private WelcomeState state = new WelcomeState();

    public WelcomeViewModel() {
        super("Welcome");
    }

    @Override
    public void firePropertyChanged() {
        support.firePropertyChange("state", null, this.state);
    }

    @Override
    public void addPropertyChangeListener(PropertyChangeListener listener) {
        support.addPropertyChangeListener(listener);
    }

    public WelcomeState getState() {
        return state;
    }
}
