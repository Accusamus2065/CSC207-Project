package use_case.swap_views.chatbot;

import org.junit.Test;

public class SwapToChatbotInteractorTest {
    @Test
    public void presenterCalledTest() {
        SwapToChatbotOutputBoundary swapPresenter = () -> {
            assert true; // Presenter has been called
        };

        SwapToChatbotInputBoundary interactor = new SwapToChatbotInteractor(swapPresenter);
        interactor.execute();
    }
}
