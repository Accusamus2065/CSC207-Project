package use_case.welcome.signup;

import use_case.welcome.WelcomeInputData;
import use_case.welcome.WelcomeOutputData;

public class WelcomeSignupInteractor implements WelcomeSignupInputBoundary {
    final WelcomeSignupOutputBoundary welcomeSignupPresenter;

    public WelcomeSignupInteractor(WelcomeSignupOutputBoundary welcomeSignupPresenter) {
        this.welcomeSignupPresenter = welcomeSignupPresenter;
    }

    @Override
    public void execute(WelcomeInputData welcomeInputData) {
        boolean isDoctor = welcomeInputData.isDoctor();
        WelcomeOutputData welcomeOutputData = new WelcomeOutputData(isDoctor);

        welcomeSignupPresenter.swapViews(welcomeOutputData);
    }
}
