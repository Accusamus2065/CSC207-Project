package interface_adapter.chat.refresh;

import use_case.chat.refresh.ConversationRefreshInputBoundary;
import use_case.chat.refresh.ConversationRefreshInputData;

import java.util.Date;

/**
 * The ConversationRefreshController serves as the controller in the refresh feature of a chat conversation.
 * It interacts with the ConversationRefreshInputBoundary to execute the refresh use case.
 */
public class ConversationRefreshController {

    // Instance of the ConversationRefreshInputBoundary to interact with the use case
    final ConversationRefreshInputBoundary conversationRefreshUseCaseInteractor;

    /**
     * Constructor for ConversationRefreshController.
     *
     * @param conversationRefreshUseCaseInteractor The use case interactor for conversation refresh.
     */
    public ConversationRefreshController(ConversationRefreshInputBoundary conversationRefreshUseCaseInteractor) {
        this.conversationRefreshUseCaseInteractor = conversationRefreshUseCaseInteractor;
    }

    /**
     * Executes the refresh operation for a conversation based on the provided sender and receiver.
     *
     * @param sender   The sender of the conversation.
     * @param receiver The receiver of the conversation.
     */
    public void executeRefresh(String sender, String receiver) {
        // Create input data with sender and receiver information
        ConversationRefreshInputData data = new ConversationRefreshInputData(sender, receiver);
        // Execute the refresh operation through the use case interactor
        conversationRefreshUseCaseInteractor.executeRefresh(data);
    }
}
