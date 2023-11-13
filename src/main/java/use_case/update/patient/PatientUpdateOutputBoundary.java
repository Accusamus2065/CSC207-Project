package use_case.update.patient;

public interface PatientUpdateOutputBoundary {

    void prepareSuccessView(PatientUpdateOutputData user);

    void prepareFailView(String error);
}
