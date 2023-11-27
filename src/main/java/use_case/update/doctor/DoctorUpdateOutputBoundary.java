package use_case.update.doctor;

public interface DoctorUpdateOutputBoundary {
    void prepareSuccessView(DoctorUpdateOutputData user);

    void prepareFailView(String error);
}
