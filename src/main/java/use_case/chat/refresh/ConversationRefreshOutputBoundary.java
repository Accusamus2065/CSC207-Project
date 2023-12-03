package use_case.chat.refresh;

/**
 * The ConversationRefreshOutputBoundary interface defines the output boundary for the conversation refresh use case.
 * It declares methods for preparing views in case of a successful refresh and in case of a failure.
 */
public interface ConversationRefreshOutputBoundary {

    /**
     * Prepares the view for a successful conversation refresh by providing the output data.
     *
     * @param messages The output data containing refreshed conversation messages.
     */
    void prepareSuccessView(ConversationRefreshOutputData messages);
    /**
     * Prepares the view for a failed conversation refresh by providing an error message.
     *
     * @param error The error message indicating the cause of the failure.
     */
}
