package interface_adapter.update.doctor;

import use_case.update.doctor.DoctorUpdateOutputBoundary;
import use_case.update.doctor.DoctorUpdateOutputData;

public class DoctorUpdatePresenter implements DoctorUpdateOutputBoundary {
    //TODO: implement this when logged-in view is done

    @Override
    public void prepareSuccessView(DoctorUpdateOutputData response) {
        assert !response.isUseCaseFailed();
    }

    @Override
    public void prepareFailView(String error) {

    }
}
