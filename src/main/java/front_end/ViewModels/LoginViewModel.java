package front_end.ViewModels;

import interface_adapter.login.LoginState;
import interface_adapter.welcome.WelcomeState;

import java.awt.*;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class LoginViewModel {
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

    public LoginState getState() {return state;}

    public void setState(LoginState state) {
        this.state = state;
    }

    private final PropertyChangeSupport support = new PropertyChangeSupport(this);

    private WelcomeState welcomeState = new WelcomeState();

    public void addPropertyChangeListener(PropertyChangeListener listener) {
        support.addPropertyChangeListener(listener);
    }


}
