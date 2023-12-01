package use_case.chatbot;

public interface DialogflowOutputBoundary {
    void prepareSuccessView(DialogflowOutputData messages);

    void prepareFailView(String error);
}