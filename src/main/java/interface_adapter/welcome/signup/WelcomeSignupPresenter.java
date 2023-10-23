package interface_adapter.welcome.signup;

import use_case.welcome.WelcomeOutputData;
import use_case.welcome.signup.WelcomeSignupOutputBoundary;

public class WelcomeSignupPresenter implements WelcomeSignupOutputBoundary {
    @Override
    public void swapViews(WelcomeOutputData welcomeOutputData) {
        // TODO: complete me; depends on SignupViewModel
        boolean isDoctor = welcomeOutputData.isDoctor();

        if (isDoctor) {
            System.out.println("Click 'Sign up'; isDoctor");
        } else {
            System.out.println("Click 'Sign up'; !isDoctor");
        }
    }
}
