package use_case.chat.refresh;

import use_case.chat.ConversationInputData;

public interface ConversationRefreshInputBoundary {
    void executeRefresh(ConversationRefreshInputData data);
}
