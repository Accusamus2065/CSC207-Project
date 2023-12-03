package interface_adapter.chat.save;

        import use_case.chat.save.ConversationSaveInputBoundary;
        import use_case.chat.save.ConversationSaveInputData;

/**
 * The ConversationSaveController serves as the controller for the save feature of a chat conversation.
 * It interacts with the ConversationSaveInputBoundary to execute the save use case.
 */
public class ConversationSaveController {
    // Instance of the ConversationSaveInputBoundary to interact with the use case
    final ConversationSaveInputBoundary conversationSaveUseCaseInteractor;

    /**
     * Constructor for ConversationSaveController.
     *
     * @param conversationSaveUseCaseInteractor The use case interactor for conversation save.
     */
    public ConversationSaveController(ConversationSaveInputBoundary conversationSaveUseCaseInteractor) {
        this.conversationSaveUseCaseInteractor = conversationSaveUseCaseInteractor;
    }

    /**
     * Executes the save operation for a conversation based on the provided sender, receiver, and message content.
     *
     * @param sender         The sender of the conversation.
     * @param receiver       The receiver of the conversation.
     * @param messageContent The content of the message to be saved.
     */
    public void executeSave(String sender, String receiver, String messageContent) {
        // Create input data with sender, receiver, and message content information
        ConversationSaveInputData data = new ConversationSaveInputData(sender, receiver, messageContent);
        // Execute the save operation through the use case interactor
        conversationSaveUseCaseInteractor.executeSave(data);
    }
}
