package interface_adapter.update.doctor;

import interface_adapter.ViewModel;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class DoctorUpdateViewModel extends ViewModel {
    public static final String TITLE_LABEL = "Doctor Update View";
    public static final String USERNAME_LABEL = "Enter username";
    public static final String PASSWORD_LABEL = "Enter password";
    public static final String REPEAT_PASSWORD_LABEL = "Enter password again";
    public static final String SPECIALITY_LABEL = "Enter speciality";
    public static final String DEGREE_LABEL = "Enter degree";
    public static final String UPDATE_BUTTON_LABEL = "Update";
    public static final String CANCEL_BUTTON_LABEL = "Cancel";
    private DoctorUpdateState state = new DoctorUpdateState();
    private final PropertyChangeSupport support = new PropertyChangeSupport(this);


    public DoctorUpdateViewModel() {
        super("doctor update");
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
