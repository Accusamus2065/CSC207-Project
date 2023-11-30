package interface_adapter.update.doctor;

import interface_adapter.signup.SignupState;
import interface_adapter.update.patient.PatientUpdateViewModel;
import use_case.update.doctor.DoctorUpdateOutputBoundary;
import use_case.update.doctor.DoctorUpdateOutputData;

public class DoctorUpdatePresenter implements DoctorUpdateOutputBoundary {
    private final DoctorUpdateViewModel doctorUpdateViewModel;

    // TODO: implement this when logged-in view is done

    public DoctorUpdatePresenter(DoctorUpdateViewModel doctorUpdateViewModel) {
        this.doctorUpdateViewModel = doctorUpdateViewModel;
    }

    @Override
    public void prepareSuccessView(DoctorUpdateOutputData response) {
        assert !response.isUseCaseFailed();
        System.out.println("Updated doctor details");
    }

    @Override
    public void prepareFailView(String error) {
        DoctorUpdateState doctorUpdateState = doctorUpdateViewModel.getState();
        doctorUpdateState.setError(error);
        doctorUpdateViewModel.firePropertyChanged();
    }
}
