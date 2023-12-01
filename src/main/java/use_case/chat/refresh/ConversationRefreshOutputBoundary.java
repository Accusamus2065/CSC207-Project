package use_case.chat.refresh;

import use_case.chat.ConversationOutputData;

public interface ConversationRefreshOutputBoundary {
    void prepareSuccessView(ConversationRefreshOutputData messages);
    void prepareFailView(String error);
}
