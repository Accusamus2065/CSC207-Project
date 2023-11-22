package interface_adapter.update.doctor;

import interface_adapter.ViewModel;

import java.awt.*;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class DoctorUpdateViewModel extends ViewModel {
    public static final String TITLE_LABEL = "Modify the Doctor Profile";
    public static final int FRAME_WIDTH_SIZE = 800;
    public static final int FRAME_HEIGHT_SIZE = 500;
    public static final Dimension PANEL_DIMENSION = new Dimension(250, 500);
    public static final Font BUTTON_FONT = new Font("Arial", Font.PLAIN, 15);
    public static final Dimension BUTTON_DIMENSION = new Dimension(110, 45);
    public static final String BACK_BUTTON_LABEL = "BACK";
    public static final Font INPUT_FIELD_FONT = new Font("Arial", Font.PLAIN, 20);
    public static final String USERNAME_FIELD_LABEL = "                   New Username:";
    public static final String PASSWORD_FIELD_LABEL = "                    New Password:";
    public static final String REPEAT_PASSWORD_FIELD_LABEL = "        Repeat New Password:";
    public static final String SPECIALTY_FIELD_LABEL = "                     New Specialty:";
    public static final String DEGREE_FIELD_LABEL = "                        New Degree:";
    public static final String SAVE_BUTTON_LABEL = "SAVE";
    private DoctorUpdateState state = new DoctorUpdateState();
    private final PropertyChangeSupport support = new PropertyChangeSupport(this);

    public DoctorUpdateViewModel() {
        super("modify doctor");
    }

    public void setState(DoctorUpdateState state) {
        this.state = state;
    }

    public DoctorUpdateState getState() {
        return state;
    }

    public void firePropertyChanged() {
        support.firePropertyChange("state", null, this.state);
    }

    public void addPropertyChangeListener(PropertyChangeListener listener) {
        support.addPropertyChangeListener(listener);
    }
}
