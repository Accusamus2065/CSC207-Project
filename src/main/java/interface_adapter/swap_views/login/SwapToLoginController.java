package interface_adapter.swap_views.login;

import use_case.swap_views.login.SwapToLoginInputBoundary;

public class SwapToLoginController {
    SwapToLoginInputBoundary swapToLoginInteractor;

    public SwapToLoginController(SwapToLoginInputBoundary swapToLoginInteractor) {
        this.swapToLoginInteractor = swapToLoginInteractor;
    }

    public void execute() {
        swapToLoginInteractor.execute();
    }
}
