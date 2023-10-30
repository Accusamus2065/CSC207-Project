package use_case.welcome.signup;

import use_case.welcome.WelcomeInputData;

public interface WelcomeSignupInputBoundary {
    void execute(WelcomeInputData welcomeInputData);
}
