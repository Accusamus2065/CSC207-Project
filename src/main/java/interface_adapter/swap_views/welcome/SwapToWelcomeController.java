package interface_adapter.swap_views.welcome;

import use_case.swap_views.welcome.SwapToWelcomeInputBoundary;

public class SwapToWelcomeController {
    SwapToWelcomeInputBoundary swaptoWelcomeInteractor;

    public SwapToWelcomeController(SwapToWelcomeInputBoundary swaptoWelcomeInteractor) {
        this.swaptoWelcomeInteractor = swaptoWelcomeInteractor;
    }

    public void execute() {
        swaptoWelcomeInteractor.execute();
    }
}
