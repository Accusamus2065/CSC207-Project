package use_case.swap_views.welcome;

import org.junit.Test;

import static org.junit.Assert.*;

public class SwapToWelcomeInteractorTest {
    @Test
    public void presenterCalledTest() {
        SwapToWelcomeOutputBoundary swapPresenter = swapToWelcomeData -> assertEquals("TestViewName", swapToWelcomeData.getViewName());

        SwapToWelcomeInteractor interactor = new SwapToWelcomeInteractor(swapPresenter);
        interactor.execute(new SwapToWelcomeData("TestViewName"));
    }
}