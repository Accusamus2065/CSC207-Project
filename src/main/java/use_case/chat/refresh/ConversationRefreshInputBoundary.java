package use_case.chat.refresh;

/**
 * The ConversationRefreshInputBoundary interface defines the input boundary for the conversation refresh use case.
 * It declares the method signature for executing the refresh operation based on the provided input data.
 */
public interface ConversationRefreshInputBoundary {

    /**
     * Executes the refresh operation for a conversation based on the provided input data.
     *
     * @param data The input data containing information necessary for the refresh operation.
     */
    void executeRefresh(ConversationRefreshInputData data);
}
