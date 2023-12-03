package interface_adapter.welcome.login;

import interface_adapter.ViewManagerModel;
import interface_adapter.login.LoginState;
import interface_adapter.login.LoginViewModel;
import use_case.welcome.WelcomeOutputData;
import use_case.welcome.login.WelcomeLoginOutputBoundary;

public class WelcomeLoginPresenter implements WelcomeLoginOutputBoundary {
    private final LoginViewModel loginViewModel;
    private ViewManagerModel viewManagerModel;

    public WelcomeLoginPresenter(LoginViewModel loginViewModel, ViewManagerModel viewManagerModel) {
        this.loginViewModel = loginViewModel;
        this.viewManagerModel = viewManagerModel;
    }

    @Override
    public void swapViews(WelcomeOutputData welcomeOutputData) {
        LoginState loginState = loginViewModel.getState();
        loginState.setDoctor(welcomeOutputData.isDoctor());
        loginState.setUsername("");
        loginState.setPassword("");
        this.loginViewModel.setState(loginState);
        loginViewModel.firePropertyChanged();

        viewManagerModel.setActiveView(loginViewModel.getViewName());
        viewManagerModel.firePropertyChanged();
        System.out.println("Press 'Login'; isDoctor=" + welcomeOutputData.isDoctor());
    }
}
