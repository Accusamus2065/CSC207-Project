package use_case.welcome.signup;

public class WelcomeSignupInteractor implements WelcomeSignupInputBoundary {
    final WelcomeSignupOutputBoundary welcomeSignupPresenter;

    public WelcomeSignupInteractor(WelcomeSignupOutputBoundary welcomeSignupPresenter) {
        this.welcomeSignupPresenter = welcomeSignupPresenter;
    }

    @Override
    public void execute() {
        welcomeSignupPresenter.swapViews();
    }
}
