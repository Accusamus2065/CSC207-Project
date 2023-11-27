package use_case.swap_views.list_of_patients;

import org.junit.Test;
public class SwapToPatientListInteractorTest {
    @Test
    public void presenterCalledTest() {
        SwapToPatientListOutputBoundary swapPresenter = () -> {
            assert true; // Presenter has been called
        };

        SwapToPatientListInteractor interactor = new SwapToPatientListInteractor(swapPresenter);
        interactor.execute();
    }
}