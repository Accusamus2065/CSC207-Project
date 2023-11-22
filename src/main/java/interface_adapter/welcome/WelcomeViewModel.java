package interface_adapter.welcome;

import interface_adapter.ViewModel;
import interface_adapter.welcome.WelcomeState;

import java.awt.*;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class WelcomeViewModel extends ViewModel {
    public static final Dimension PANEL_DIMENSION = new Dimension(800, 500);
    public static final String LOGIN_BUTTON_LABEL = "Log In";
    public static final String SIGNUP_BUTTON_LABEL = "Sign Up";
    public static final Font BUTTON_FONT = new Font("Arial", Font.PLAIN, 20);
    public static final Dimension BUTTON_DIMENSION = new Dimension(100, 70);
    public static final String DOCTOR_CHECKBOX_LABEL = "I AM A DOCTOR";

    private WelcomeState state = new WelcomeState();
    private final PropertyChangeSupport support = new PropertyChangeSupport(this);

    public WelcomeViewModel() {
        super("welcome");
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
