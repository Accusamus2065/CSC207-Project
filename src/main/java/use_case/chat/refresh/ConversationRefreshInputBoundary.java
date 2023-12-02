package use_case.chat.refresh;

public interface ConversationRefreshInputBoundary {
    void executeRefresh(ConversationRefreshInputData data);
}
