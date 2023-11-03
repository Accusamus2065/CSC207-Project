package interface_adapter.chat;

import entity.chat.Conversation;
import interface_adapter.ViewModel;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class ConversationViewModel extends ViewModel {

    public static final String TITLE_LABEL = "Sign Up View";

    public static final String CANCEL_BUTTON_LABEL = "Send";
    private ConversationState state = new ConversationState();
    private final PropertyChangeSupport support = new PropertyChangeSupport(this);


    public ConversationViewModel() {
        super("Conversation");
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
