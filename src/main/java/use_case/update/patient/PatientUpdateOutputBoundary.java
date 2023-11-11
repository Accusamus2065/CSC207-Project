package use_case.update.patient;

import use_case.signup.SignupOutputData;

public interface PatientUpdateOutputBoundary {

    void prepareSuccessView(SignupOutputData user);

    void prepareFailView(String error);
}
