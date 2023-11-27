package use_case.swap_views.update.patient;

public class SwapToPatientUpdateInteractor implements SwapToPatientUpdateInputBoundary {
    SwapToPatientUpdateOutputBoundary swapToPatientUpdatePresenter;

    public SwapToPatientUpdateInteractor(SwapToPatientUpdateOutputBoundary swapToPatientUpdatePresenter) {
        this.swapToPatientUpdatePresenter = swapToPatientUpdatePresenter;
    }

    @Override
    public void execute(String username) {
        swapToPatientUpdatePresenter.execute(username);
    }
}
