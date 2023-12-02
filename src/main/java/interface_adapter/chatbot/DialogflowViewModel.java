package interface_adapter.chatbot;

import interface_adapter.ViewModel;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class DialogflowViewModel extends ViewModel {
    public static final String TITLE_LABEL = "Dialogflow";

    private DialogflowState state = new DialogflowState();
    private final PropertyChangeSupport support = new PropertyChangeSupport(this);


    public DialogflowViewModel() {
        super("dialogflow view");
    }

    public void setState(DialogflowState state) {
        this.state = state;
    }

    public DialogflowState getState() {
        return state;
    }

    public void firePropertyChanged() {
        support.firePropertyChange("state", null, this.state);
    }

    public void addPropertyChangeListener(PropertyChangeListener listener) {
        support.addPropertyChangeListener(listener);
    }
}
