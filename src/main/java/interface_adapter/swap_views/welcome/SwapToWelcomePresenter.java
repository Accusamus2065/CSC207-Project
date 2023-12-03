package interface_adapter.swap_views.welcome;

import interface_adapter.ViewManagerModel;
import interface_adapter.login.LoginState;
import interface_adapter.login.LoginViewModel;
import interface_adapter.signup.SignupState;
import interface_adapter.signup.SignupViewModel;
import interface_adapter.welcome.WelcomeViewModel;
import use_case.swap_views.welcome.SwapToWelcomeData;
import use_case.swap_views.welcome.SwapToWelcomeOutputBoundary;

public class SwapToWelcomePresenter implements SwapToWelcomeOutputBoundary {
    private final ViewManagerModel viewManagerModel;
    private final WelcomeViewModel welcomeViewModel;
    private final LoginViewModel loginViewModel;
    private final SignupViewModel signupViewModel;

    public SwapToWelcomePresenter(ViewManagerModel viewManagerModel, WelcomeViewModel welcomeViewModel, LoginViewModel loginViewModel, SignupViewModel signupViewModel) {
        this.viewManagerModel = viewManagerModel;
        this.welcomeViewModel = welcomeViewModel;
        this.loginViewModel = loginViewModel;
        this.signupViewModel = signupViewModel;
    }

    @Override
    public void execute(SwapToWelcomeData swapToWelcomeData) {
        if (swapToWelcomeData.getViewName().equals(loginViewModel.getViewName())) {
            loginViewModel.setState(new LoginState());
        } else {
            signupViewModel.setState(new SignupState());
        }
        viewManagerModel.setActiveView(welcomeViewModel.getViewName());
        viewManagerModel.firePropertyChanged();
    }
}
