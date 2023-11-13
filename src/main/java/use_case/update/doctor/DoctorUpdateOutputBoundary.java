package use_case.update.doctor;

import use_case.signup.SignupOutputData;

public interface DoctorUpdateOutputBoundary {
    void prepareSuccessView(DoctorUpdateOutputData user);

    void prepareFailView(String error);
}
