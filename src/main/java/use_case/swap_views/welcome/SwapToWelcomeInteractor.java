package use_case.swap_views.welcome;

public class SwapToWelcomeInteractor implements SwapToWelcomeInputBoundary {
    SwapToWelcomeOutputBoundary swapToWelcomePresenter;

    public SwapToWelcomeInteractor(SwapToWelcomeOutputBoundary swapToWelcomePresenter) {
        this.swapToWelcomePresenter = swapToWelcomePresenter;
    }

    @Override
    public void execute(SwapToWelcomeData swap) {
        swapToWelcomePresenter.execute(swap);
    }
}
