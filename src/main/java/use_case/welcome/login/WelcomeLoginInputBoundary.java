package use_case.welcome.login;

import use_case.welcome.WelcomeInputData;

public interface WelcomeLoginInputBoundary {
    void execute(WelcomeInputData welcomeInputData);
}
