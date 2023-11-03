package use_case.chat;

public interface ConversationOutputBoundary {
    void prepareSuccessView(ConversationOutputData user);

    void prepareFailView(String error);
}