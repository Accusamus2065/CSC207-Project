package interface_adapter.welcome.login;

import use_case.welcome.WelcomeInputData;
import use_case.welcome.login.WelcomeLoginInputBoundary;

public class WelcomeLoginController {

    final WelcomeLoginInputBoundary welcomeLoginInteractor;

    public WelcomeLoginController(WelcomeLoginInputBoundary welcomeLoginInteractor) {
        this.welcomeLoginInteractor = welcomeLoginInteractor;
    }

    public void execute(boolean isDoctor) {
        WelcomeInputData welcomeInputData = new WelcomeInputData(isDoctor);

        welcomeLoginInteractor.execute(welcomeInputData);
    }
}
