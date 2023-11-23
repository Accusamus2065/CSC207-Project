package use_case.choosepatient;

public interface ChoosePatientOutputBoundary {
    void prepareSuccessView(ChoosePatientOutputData user);

    void prepareFailView(String error);
}