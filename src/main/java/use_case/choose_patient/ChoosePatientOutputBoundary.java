package use_case.choose_patient;

public interface ChoosePatientOutputBoundary {
    void prepareSuccessView(ChoosePatientOutputData user);

    void prepareFailView(String error);
}