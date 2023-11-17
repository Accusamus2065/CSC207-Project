package interface_adapter.swap_views.welcome;

import use_case.swap_views.SwaptoWelcomeInputBoundary;

public class SwaptoWelcomeController {
    SwaptoWelcomeInputBoundary swaptoWelcomeInteractor;

    public SwaptoWelcomeController(SwaptoWelcomeInputBoundary swaptoWelcomeInteractor) {
        this.swaptoWelcomeInteractor = swaptoWelcomeInteractor;
    }

    public void execute() {
        swaptoWelcomeInteractor.execute();
    }
}
