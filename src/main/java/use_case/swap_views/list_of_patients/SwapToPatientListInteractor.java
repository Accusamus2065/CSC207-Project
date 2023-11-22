package use_case.swap_views.list_of_patients;

public class SwapToPatientListInteractor implements SwapToPatientListInputBoundary {
    private final SwapToPatientListOutputBoundary swapToPatientPresenter;

    public SwapToPatientListInteractor(SwapToPatientListOutputBoundary swapToPatientPresenter) {
        this.swapToPatientPresenter = swapToPatientPresenter;
    }


    @Override
    public void execute() {
        swapToPatientPresenter.swapViews();
    }
}
