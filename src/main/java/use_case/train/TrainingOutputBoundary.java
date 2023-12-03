package use_case.train;

public interface TrainingOutputBoundary {
    void prepareSuccessView(TrainingOutputData messages);

    void prepareFailView(String error);
}