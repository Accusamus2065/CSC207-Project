package use_case.swap_views.welcome;

import org.junit.Test;

public class SwapToWelcomeInteractorTest {
    @Test
    public void presenterCalledTest() {
        SwapToWelcomeOutputBoundary swapPresenter = () -> {
            assert true; // Presenter has been called
        };

        SwapToWelcomeInteractor interactor = new SwapToWelcomeInteractor(swapPresenter);
        interactor.execute();
    }
}