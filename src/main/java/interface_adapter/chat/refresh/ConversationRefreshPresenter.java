package interface_adapter.chat.refresh;

import interface_adapter.ViewManagerModel;
import use_case.chat.refresh.ConversationRefreshOutputBoundary;
import use_case.chat.refresh.ConversationRefreshOutputData;

/**
 * The ConversationRefreshPresenter acts as a presenter for the output of the conversation refresh use case.
 * It prepares the view model and notifies the view manager about the success or failure of the operation.
 */
public class ConversationRefreshPresenter implements ConversationRefreshOutputBoundary {

    // The view model for conversation refresh
    private final ConversationRefreshViewModel conversationRefreshViewModel;
    // The view manager model for handling views
    private final ViewManagerModel viewManagerModel;

    /**
     * Constructor for ConversationRefreshPresenter.
     *
     * @param viewManagerModel             The view manager model.
     * @param conversationRefreshViewModel The view model for conversation refresh.
     */
    public ConversationRefreshPresenter(ViewManagerModel viewManagerModel, ConversationRefreshViewModel conversationRefreshViewModel) {
        this.viewManagerModel = viewManagerModel;
        this.conversationRefreshViewModel = conversationRefreshViewModel;
    }

    /**
     * Prepares the view with the successful output of the conversation refresh use case.
     *
     * @param messages The output data containing refreshed conversation messages.
     */
    @Override
    public void prepareSuccessView(ConversationRefreshOutputData messages) {
        // Get the current state of the conversation refresh view model
        ConversationRefreshState convoRefreshState = conversationRefreshViewModel.getState();
        // Set the updated messages in the view model state
        convoRefreshState.setMessages(messages.getMessages());
        // Update the view model state
        this.conversationRefreshViewModel.setState(convoRefreshState);
        // Notify observers about the property change
        conversationRefreshViewModel.firePropertyChanged();
        // Print the updated messages to the console (for debugging)
        System.out.println(conversationRefreshViewModel.getState().getMessages());

        // Set the active view in the view manager
        viewManagerModel.setActiveView(conversationRefreshViewModel.getViewName());
        // Notify observers about the property change in the view manager
        viewManagerModel.firePropertyChanged();
    }
    /*
      Handles the preparation of the view in case of a failed conversation refresh.

      @param error The error message describing the failure.
     */
    // Implementation for handling failure view (currently empty)
    // Could include logic for displaying an error message to the user.
}
