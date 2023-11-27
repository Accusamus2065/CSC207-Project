package interface_adapter.chat;

import interface_adapter.ViewModel;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class ConversationViewModel extends ViewModel {

    public static final String TITLE_LABEL = "Conversation";

    private ConversationState state = new ConversationState();
    private final PropertyChangeSupport support = new PropertyChangeSupport(this);


    public ConversationViewModel() {
        super("conversation view");
    }

    public void setState(ConversationState state) {
        this.state = state;
    }

    public ConversationState getState() {
        return state;
    }

    public void firePropertyChanged() {
        support.firePropertyChange("state", null, this.state);
    }

    public void addPropertyChangeListener(PropertyChangeListener listener) {
        support.addPropertyChangeListener(listener);
    }


}