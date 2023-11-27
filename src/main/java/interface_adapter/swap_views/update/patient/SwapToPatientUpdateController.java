package interface_adapter.swap_views.update.patient;

import use_case.swap_views.update.patient.SwapToPatientUpdateInputBoundary;

public class SwapToPatientUpdateController {
    SwapToPatientUpdateInputBoundary swapToPatientUpdateInteractor;

    public SwapToPatientUpdateController(SwapToPatientUpdateInputBoundary swapToPatientUpdateInteractor) {
        this.swapToPatientUpdateInteractor = swapToPatientUpdateInteractor;
    }

    public void execute(String username) {
        swapToPatientUpdateInteractor.execute(username);
    }
}
