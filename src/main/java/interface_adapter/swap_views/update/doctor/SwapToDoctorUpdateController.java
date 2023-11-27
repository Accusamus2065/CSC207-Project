package interface_adapter.swap_views.update.doctor;

import use_case.swap_views.update.doctor.SwapToDoctorUpdateInputBoundary;

public class SwapToDoctorUpdateController {
    SwapToDoctorUpdateInputBoundary swapToDoctorUpdateInteractor;

    public SwapToDoctorUpdateController(SwapToDoctorUpdateInputBoundary swapToDoctorUpdateInteractor) {
        this.swapToDoctorUpdateInteractor = swapToDoctorUpdateInteractor;
    }

    public void execute() {
        swapToDoctorUpdateInteractor.execute();
    }
}
