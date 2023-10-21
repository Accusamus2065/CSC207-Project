package use_case.welcome.login;

public class WelcomeLoginInteractor implements WelcomeLoginInputBoundary {

    final WelcomeLoginOutputBoundary welcomeLoginPresenter;

    public WelcomeLoginInteractor(WelcomeLoginOutputBoundary welcomeLoginPresenter) {
        this.welcomeLoginPresenter = welcomeLoginPresenter;
    }

    @Override
    public void execute() {
        welcomeLoginPresenter.swapViews();
    }
}
