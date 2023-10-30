package use_case.welcome.login;

import use_case.welcome.WelcomeInputData;
import use_case.welcome.WelcomeOutputData;

public class WelcomeLoginInteractor implements WelcomeLoginInputBoundary {

    final WelcomeLoginOutputBoundary welcomeLoginPresenter;

    public WelcomeLoginInteractor(WelcomeLoginOutputBoundary welcomeLoginPresenter) {
        this.welcomeLoginPresenter = welcomeLoginPresenter;
    }

    @Override
    public void execute(WelcomeInputData welcomeInputData) {
        boolean isDoctor = welcomeInputData.isDoctor();
        WelcomeOutputData welcomeOutputData = new WelcomeOutputData(isDoctor);

        welcomeLoginPresenter.swapViews(welcomeOutputData);
    }
}
