package interface_adapter.welcome.login;

import use_case.welcome.WelcomeOutputData;
import use_case.welcome.login.WelcomeLoginOutputBoundary;

public class WelcomeLoginPresenter implements WelcomeLoginOutputBoundary {

    @Override
    public void swapViews(WelcomeOutputData welcomeOutputData) {
        // TODO: complete me; depends on SignupViewModel
        boolean isDoctor = welcomeOutputData.isDoctor();

        if (isDoctor) {
            System.out.println("Click 'Log in'; isDoctor");
        } else {
            System.out.println("Click 'Log in'; !isDoctor");
        }
    }
}
