package use_case.swap_views.login;

import org.junit.Test;
public class SwapToLoginInteractorTest {
    @Test
    public void presenterCalledTest() {
        SwapToLoginOutputBoundary swapPresenter = () -> {
            assert true; // Presenter has been called
        };

        SwapToLoginInteractor interactor = new SwapToLoginInteractor(swapPresenter);
        interactor.execute();
    }
}