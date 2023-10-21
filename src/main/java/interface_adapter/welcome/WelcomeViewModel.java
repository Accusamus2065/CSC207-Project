package interface_adapter.welcome;

import interface_adapter.ViewModel;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class WelcomeViewModel extends ViewModel {
    public static final String TITLE_LABEL = "Welcome!";
    public static final String SIGNUP_BUTTON_LABEL = "Sign Up";
    public static final String LOGIN_BUTTON_LABEL = "Log In";
    private final PropertyChangeSupport support = new PropertyChangeSupport(this);

    public WelcomeViewModel() {
        super("Welcome");
    }

    // The methods below should never be called since WelcomeView should never be modified
    @Override
    public void firePropertyChanged() {
        support.firePropertyChange("state", null, null);
    }

    @Override
    public void addPropertyChangeListener(PropertyChangeListener listener) {
        support.addPropertyChangeListener(listener);
    }
}
