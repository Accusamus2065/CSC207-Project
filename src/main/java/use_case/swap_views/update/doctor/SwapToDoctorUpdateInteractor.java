package use_case.swap_views.update.doctor;

public class SwapToDoctorUpdateInteractor implements SwapToDoctorUpdateInputBoundary {
    SwapToDoctorUpdateOutputBoundary swapToDoctorUpdatePresenter;

    public SwapToDoctorUpdateInteractor(SwapToDoctorUpdateOutputBoundary swapToDoctorUpdatePresenter) {
        this.swapToDoctorUpdatePresenter = swapToDoctorUpdatePresenter;
    }

    @Override
    public void execute() {
        swapToDoctorUpdatePresenter.execute();
    }
}
