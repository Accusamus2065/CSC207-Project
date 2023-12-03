//package use_case.chat.save;
//
//public interface ConversationSaveInputBoundary {
//    void executeSave(ConversationSaveInputData data);
//}
package use_case.chat.save;

/**
 * The ConversationSaveInputBoundary interface defines the input boundary for the conversation save use case.
 * It declares the method signature for executing the save operation based on the provided input data.
 */
public interface ConversationSaveInputBoundary {

    /**
     * Executes the save operation for a conversation based on the provided input data.
     *
     * @param data The input data containing information necessary for the save operation.
     */
    void executeSave(ConversationSaveInputData data);
}
