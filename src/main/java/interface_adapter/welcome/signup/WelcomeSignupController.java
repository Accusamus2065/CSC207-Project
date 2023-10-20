package interface_adapter.welcome.signup;

import use_case.welcome.signup.WelcomeSignupInputBoundary;

public class WelcomeSignupController {

    final WelcomeSignupInputBoundary welcomeSignupInteractor;

    public WelcomeSignupController(WelcomeSignupInputBoundary welcomeSignupInteractor) {
        this.welcomeSignupInteractor = welcomeSignupInteractor;
    }

    public void execute() {
        welcomeSignupInteractor.execute();
    }
}
