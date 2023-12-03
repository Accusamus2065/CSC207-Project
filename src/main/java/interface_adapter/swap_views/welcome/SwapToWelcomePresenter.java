package interface_adapter.swap_views.welcome;

import interface_adapter.ViewManagerModel;
import interface_adapter.choose_patient.ChoosePatientState;
import interface_adapter.choose_patient.ChoosePatientViewModel;
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
    private final ChoosePatientViewModel choosePatientViewModel;

    public SwapToWelcomePresenter(ViewManagerModel viewManagerModel,
                                  WelcomeViewModel welcomeViewModel,
                                  LoginViewModel loginViewModel,
                                  SignupViewModel signupViewModel,
                                  ChoosePatientViewModel choosePatientViewModel) {
        this.viewManagerModel = viewManagerModel;
        this.welcomeViewModel = welcomeViewModel;
        this.loginViewModel = loginViewModel;
        this.signupViewModel = signupViewModel;
        this.choosePatientViewModel = choosePatientViewModel;
    }

    @Override
    public void execute(SwapToWelcomeData swapToWelcomeData) {
        switch (swapToWelcomeData.getViewName()) {
            case "log in": loginViewModel.setState(new LoginState());
            case "sign up": signupViewModel.setState(new SignupState());
            case "choose patient": choosePatientViewModel.setState(new ChoosePatientState());
        }

        viewManagerModel.setActiveView(welcomeViewModel.getViewName());
        viewManagerModel.firePropertyChanged();
    }
}
