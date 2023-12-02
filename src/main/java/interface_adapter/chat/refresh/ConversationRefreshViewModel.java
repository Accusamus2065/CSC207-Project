package interface_adapter.chat.refresh;

import interface_adapter.ViewModel;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class ConversationRefreshViewModel extends ViewModel {

    public static final String TITLE_LABEL = "Conversation";

    private ConversationRefreshState state = new ConversationRefreshState();
    private final PropertyChangeSupport support = new PropertyChangeSupport(this);
    public ConversationRefreshViewModel() {
        super("conversation view");
    }
    public void setState(ConversationRefreshState state) {
        this.state = state;
    }
    public ConversationRefreshState getState() {
        return state;
    }
    public void firePropertyChanged() {
        support.firePropertyChange("state", null, this.state);
    }
    public void addPropertyChangeListener(PropertyChangeListener listener) {
        support.addPropertyChangeListener(listener);
    }
}
