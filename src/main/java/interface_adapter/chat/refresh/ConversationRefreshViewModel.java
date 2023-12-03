package interface_adapter.chat.refresh;

        import interface_adapter.ViewModel;

        import java.beans.PropertyChangeListener;
        import java.beans.PropertyChangeSupport;

/**
 * The ConversationRefreshViewModel serves as the view model for the conversation refresh feature.
 * It encapsulates the state of the view and provides methods for updating and notifying changes.
 */
public class ConversationRefreshViewModel extends ViewModel {

    // Constant representing the title label for the conversation view
    public static final String TITLE_LABEL = "Conversation";

    // The state of the conversation refresh view
    private ConversationRefreshState state = new ConversationRefreshState();

    // PropertyChangeSupport for managing property change events
    private final PropertyChangeSupport support = new PropertyChangeSupport(this);

    /**
     * Constructor for ConversationRefreshViewModel.
     * Initializes the view model with a specified view name.
     */
    public ConversationRefreshViewModel() {
        super("conversation view");
    }

    /**
     * Setter for updating the state of the conversation refresh view.
     *
     * @param state The new state of the conversation refresh view.
     */
    public void setState(ConversationRefreshState state) {
        this.state = state;
    }

    /**
     * Getter for retrieving the current state of the conversation refresh view.
     *
     * @return The current state of the conversation refresh view.
     */
    public ConversationRefreshState getState() {
        return state;
    }

    /**
     * Notifies observers about a property change in the view model.
     */
    public void firePropertyChanged() {
        // Fire a property change event for the "state" property
        support.firePropertyChange("state", null, this.state);
    }

    /**
     * Adds a property change listener to the view model.
     *
     * @param listener The property change listener to add.
     */
    public void addPropertyChangeListener(PropertyChangeListener listener) {
        // Add a property change listener to the support
        support.addPropertyChangeListener(listener);
    }
}
