package interface_adapter.welcome.signup;

import interface_adapter.ViewManagerModel;
import interface_adapter.signup.SignupState;
import interface_adapter.signup.SignupViewModel;
import use_case.welcome.WelcomeOutputData;
import use_case.welcome.signup.WelcomeSignupOutputBoundary;

public class WelcomeSignupPresenter implements WelcomeSignupOutputBoundary {
    private final SignupViewModel signupViewModel;
    private ViewManagerModel viewManagerModel;

    public WelcomeSignupPresenter(SignupViewModel signupViewModel, ViewManagerModel viewManagerModel) {
        this.signupViewModel = signupViewModel;
        this.viewManagerModel = viewManagerModel;
    }

    @Override
    public void swapViews(WelcomeOutputData welcomeOutputData) {
        SignupState signupState = signupViewModel.getState();
        signupState.setDoctor(welcomeOutputData.isDoctor());
        this.signupViewModel.setState(signupState);
        signupViewModel.firePropertyChanged();

        viewManagerModel.setActiveView(signupViewModel.getViewName());
        viewManagerModel.firePropertyChanged();
        System.out.println("Press 'Signup'; isDoctor=" + welcomeOutputData.isDoctor());
    }
}
