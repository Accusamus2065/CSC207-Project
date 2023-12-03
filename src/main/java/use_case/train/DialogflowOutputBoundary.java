package use_case.train;

public interface DialogflowOutputBoundary {
    void prepareSuccessView(DialogflowOutputData messages);

    void prepareFailView(String error);
}