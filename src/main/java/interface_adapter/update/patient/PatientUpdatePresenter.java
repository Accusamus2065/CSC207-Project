package interface_adapter.update.patient;

import use_case.update.patient.PatientUpdateOutputBoundary;
import use_case.update.patient.PatientUpdateOutputData;

public class PatientUpdatePresenter implements PatientUpdateOutputBoundary {
    //TODO: implement this when logged-in view is done

    @Override
    public void prepareSuccessView(PatientUpdateOutputData user) {
        assert !user.isUseCaseFailed();
    }

    @Override
    public void prepareFailView(String error) {

    }
}
