package use_case.chat.refresh;

public interface ConversationRefreshOutputBoundary {
    void prepareSuccessView(ConversationRefreshOutputData messages);
    void prepareFailView(String error);
}