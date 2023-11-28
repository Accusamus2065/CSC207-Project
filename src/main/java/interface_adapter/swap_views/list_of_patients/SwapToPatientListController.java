package interface_adapter.swap_views.list_of_patients;

import use_case.swap_views.list_of_patients.SwapToPatientListInputBoundary;

public class SwapToPatientListController {
    private final SwapToPatientListInputBoundary swapToPatientListInteractor;

    public SwapToPatientListController(SwapToPatientListInputBoundary swapToPatientListInteractor) {
        this.swapToPatientListInteractor = swapToPatientListInteractor;
    }

    public void execute() {
        swapToPatientListInteractor.execute();
    }
}
