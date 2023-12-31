package interface_adapter.swap_views.login;

import interface_adapter.ViewManagerModel;
import interface_adapter.login.LoginState;
import interface_adapter.login.LoginViewModel;
import use_case.swap_views.login.SwapToLoginOutputBoundary;

public class SwapToLoginPresenter implements SwapToLoginOutputBoundary {
    ViewManagerModel viewManagerModel;
    LoginViewModel loginViewModel;

    public SwapToLoginPresenter(ViewManagerModel viewManagerModel, LoginViewModel loginViewModel) {
        this.viewManagerModel = viewManagerModel;
        this.loginViewModel = loginViewModel;
    }

    @Override
    public void execute() {
        LoginState state = loginViewModel.getState();
        state.setUsername("");
        state.setPassword("");
        state.setError(null);
        viewManagerModel.setActiveView(loginViewModel.getViewName());
        viewManagerModel.firePropertyChanged();
    }
}
