package use_case.chat;

public interface ConversationOutputBoundary {
    void prepareSuccessView(ConversationOutputData messages);

    void prepareFailView(String error);
}