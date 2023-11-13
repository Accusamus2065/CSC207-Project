package interface_adapter.update.patient;

import interface_adapter.ViewModel;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class PatientUpdateViewModel extends ViewModel {

    public static final String TITLE_LABEL = "Patient Update View";
    public static final String USERNAME_LABEL = "Enter username";
    public static final String PASSWORD_LABEL = "Enter password";
    public static final String REPEAT_PASSWORD_LABEL = "Enter password again";
    public static final String SEX_LABEL = "Enter sex";
    public static final String GENDER_LABEL = "Enter gender";
    public static final String HEIGHT_LABEL = "Enter height";
    public static final String WEIGHT_LABEL = "Enter weight";
    public static final String BLOOD_TYPE_LABEL = "Enter blood type";
    public static final String UPDATE_BUTTON_LABEL = "Update";
    public static final String CANCEL_BUTTON_LABEL = "Cancel";
    private PatientUpdateState state = new PatientUpdateState();
    private final PropertyChangeSupport support = new PropertyChangeSupport(this);


    public PatientUpdateViewModel() {
        super("patient update");
    }

    public void setState(PatientUpdateState state) {
        this.state = state;
    }

    public PatientUpdateState getState() {
        return state;
    }

    public void firePropertyChanged() {
        support.firePropertyChange("state", null, this.state);
    }

    public void addPropertyChangeListener(PropertyChangeListener listener) {
        support.addPropertyChangeListener(listener);
    }
}
