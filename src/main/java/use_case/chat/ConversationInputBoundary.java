package use_case.chat;

public interface ConversationInputBoundary {
    void executeSave(ConversationInputData data);

    void executeRefresh(ConversationInputData data);
}
