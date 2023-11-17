package interface_adapter.choosepatient;

import interface_adapter.ViewModel;

import java.awt.*;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class ChoosePatientViewModel extends ViewModel {
    public static final String TITLE_LABEL = "List of Patients";
    public static final int FRAME_WIDTH_SIZE = 800;
    public static final int FRAME_HEIGHT_SIZE = 500;
    public static final Dimension PANEL_DIMENSION = new Dimension(800, 500);
    public static final String LOGOUT_BUTTON_LABEL = "Log Out";
    public static final String MAIN_LABEL = "List of Patients";
    public static final Font MAIN_LABEL_FONT = new Font("Sans-serif", Font.BOLD, 40);
    public static final String MODIFY_BUTTON_LABEL = "Modify Profile";
    public static final Font BUTTON_FONT = new Font("Arial", Font.PLAIN, 15);
    public static final Dimension BUTTON_DIMENSION = new Dimension(110, 45);

    private ChoosePatientState state = new ChoosePatientState();

    public ChoosePatientViewModel() {
        super("choose patient");
    }

    public void setState(ChoosePatientState state) {
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

    public ChoosePatientState getState() {
        return state;
    }
}
