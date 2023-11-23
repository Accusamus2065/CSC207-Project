package use_case.swap_views.login;

public class SwapToLoginInteractor implements SwapToLoginInputBoundary {
    SwapToLoginOutputBoundary swapToLoginPresenter;

    public SwapToLoginInteractor(SwapToLoginOutputBoundary swapToLoginPresenter) {
        this.swapToLoginPresenter = swapToLoginPresenter;
    }

    @Override
    public void execute() {
        swapToLoginPresenter.execute();
    }
}
