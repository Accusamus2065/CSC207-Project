package use_case.swap_views.chatbot;

import org.junit.Test;

public class SwapToDialogflowInteractorTest {
    @Test
    public void presenterCalledTest() {
        SwapToDialogflowOutputBoundary swapPresenter = () -> {
            assert true; // Presenter has been called
        };

        SwapToDialogflowInputBoundary interactor = new SwapToDialogflowInteractor(swapPresenter);
        interactor.execute();
    }
}
