package interface_adapter.signup;

import interface_adapter.ViewModel;

import java.awt.*;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class SignupViewModel extends ViewModel {
    public static final String TITLE_LABEL = "Signup Screen";
    public static final int FRAME_WIDTH_SIZE = 800;
    public static final int FRAME_HEIGHT_SIZE = 500;
    public static final Dimension PANEL_DIMENSION = new Dimension(250, 500);
    public static final String USERNAME_FIELD_LABEL = "          Username";
    public static final String PASSWORD_FIELD_LABEL = "           Password";
    public static final String REPEAT_PASSWORD_FIELD_LABEL = "Repeat Password";
    public static final Font INPUT_FIELD_FONT = new Font("Arial", Font.PLAIN, 20);
    public static final String SIGNUP_BUTTON_LABEL = "Sign Up";
    public static final String CANCEL_BUTTON_LABEL = "Cancel";
    public static final Font BUTTON_FONT = new Font("Arial", Font.PLAIN, 20);
    public static final Dimension BUTTON_DIMENSION = new Dimension(110, 65);
    private SignupState state = new SignupState();
    private final PropertyChangeSupport support = new PropertyChangeSupport(this);

    public SignupViewModel() {
        super("sign up");
    }

    public void setState(SignupState state) {
        this.state = state;
    }

    public SignupState getState() {
        return state;
    }

    public void firePropertyChanged() {
        support.firePropertyChange("state", null, this.state);
    }

    public void addPropertyChangeListener(PropertyChangeListener listener) {
        support.addPropertyChangeListener(listener);
    }
}
