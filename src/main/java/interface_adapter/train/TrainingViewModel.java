package interface_adapter.train;

import interface_adapter.ViewModel;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class TrainingViewModel extends ViewModel {
    public static final String TITLE_LABEL = "Training";

    private TrainingState state = new TrainingState();
    private final PropertyChangeSupport support = new PropertyChangeSupport(this);


    public TrainingViewModel() {
        super("training view");
    }

    public void setState(TrainingState state) {
        this.state = state;
    }

    public TrainingState getState() {
        return state;
    }

    public void firePropertyChanged() {
        support.firePropertyChange("state", null, this.state);
    }

    public void addPropertyChangeListener(PropertyChangeListener listener) {
        support.addPropertyChangeListener(listener);
    }
}
