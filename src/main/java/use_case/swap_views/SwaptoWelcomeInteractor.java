package use_case.swap_views;

public class SwaptoWelcomeInteractor implements SwaptoWelcomeInputBoundary {
    SwaptoWelcomeOutputBoundary swaptoWelcomePresenter;

    public SwaptoWelcomeInteractor(SwaptoWelcomeOutputBoundary swaptoWelcomePresenter) {
        this.swaptoWelcomePresenter = swaptoWelcomePresenter;
    }

    @Override
    public void execute() {
        swaptoWelcomePresenter.execute();
    }
}
