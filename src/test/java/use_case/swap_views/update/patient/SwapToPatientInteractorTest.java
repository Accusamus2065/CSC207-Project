package use_case.swap_views.update.patient;

import org.junit.Test;

public class SwapToPatientInteractorTest {
    @Test
    public void presenterCalledTest() {
        SwapToPatientUpdateOutputBoundary swapPresenter = username -> {
            assert true;
        };

        SwapToPatientUpdateInputBoundary interactor = new SwapToPatientUpdateInteractor(swapPresenter);
        interactor.execute("User1");
    }
}
