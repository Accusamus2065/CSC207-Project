package interface_adapter.login;

import interface_adapter.ViewModel;

import java.awt.*;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class LoginViewModel extends ViewModel {
    public static final String TITLE_LABEL = "Login Screen";
    public static final int FRAME_WIDTH_SIZE = 800;
    public static final int FRAME_HEIGHT_SIZE = 500;
    public static final Dimension PANEL_DIMENSION = new Dimension(250, 500);
    public static final String USERNAME_FIELD_LABEL = "Username";
    public static final String PASSWORD_FIELD_LABEL = "Password";
    public static final Font INPUT_FIELD_FONT = new Font("Arial", Font.PLAIN, 20);
    public static final String LOGIN_BUTTON_LABEL = "Log In";
    public static final String CANCEL_BUTTON_LABEL = "Cancel";
    public static final Font BUTTON_FONT = new Font("Arial", Font.PLAIN, 20);
    public static final Dimension BUTTON_DIMENSION = new Dimension(100, 70);

    private LoginState state = new LoginState();

    public LoginViewModel() {
        super("log in");
    }

    public void setState(LoginState state) {
        this.state = state;
    }

    private final PropertyChangeSupport support = new PropertyChangeSupport(this);

    // This is what the Signup Presenter will call to let the ViewModel know
    // to alert the View
    public void firePropertyChanged() {
        support.firePropertyChange("state", null, this.state);
    }

    public void addPropertyChangeListener(PropertyChangeListener listener) {
        support.addPropertyChangeListener(listener);
    }

    public LoginState getState() {
        return state;
    }
}
