package app;

import interface_adapter.ViewManagerModel;
import interface_adapter.login.LoginViewModel;
import interface_adapter.welcome.WelcomeViewModel;
import interface_adapter.welcome.login.WelcomeLoginController;
import interface_adapter.welcome.login.WelcomeLoginPresenter;
import interface_adapter.welcome.signup.WelcomeSignupController;
import interface_adapter.welcome.signup.WelcomeSignupPresenter;
import interface_adapter.signup.SignupViewModel;
import use_case.welcome.login.WelcomeLoginInputBoundary;
import use_case.welcome.login.WelcomeLoginInteractor;
import use_case.welcome.login.WelcomeLoginOutputBoundary;
import use_case.welcome.signup.WelcomeSignupInputBoundary;
import use_case.welcome.signup.WelcomeSignupInteractor;
import use_case.welcome.signup.WelcomeSignupOutputBoundary;
import view.WelcomeView;

public class WelcomeUseCaseFactory {

    /** Prevent instantiation. */
    private WelcomeUseCaseFactory() {}

    public static WelcomeView create(WelcomeViewModel welcomeViewModel,
                                     SignupViewModel signupViewModel,
                                     LoginViewModel loginViewModel,
                                     ViewManagerModel viewManagerModel) {
        WelcomeSignupController signupController = createSignupUseCase(signupViewModel, viewManagerModel);
        WelcomeLoginController loginController = createLoginUseCase(loginViewModel, viewManagerModel);
         return new WelcomeView(welcomeViewModel, signupController, loginController);
    }

    private static WelcomeSignupController createSignupUseCase(SignupViewModel signupViewModel,
                                                               ViewManagerModel viewManagerModel) {
        WelcomeSignupOutputBoundary welcomeSignupOutputBoundary = new WelcomeSignupPresenter(signupViewModel, viewManagerModel);
        WelcomeSignupInputBoundary welcomeSignupInputBoundary = new WelcomeSignupInteractor(welcomeSignupOutputBoundary);

        return new WelcomeSignupController(welcomeSignupInputBoundary);
    }

    private static WelcomeLoginController createLoginUseCase(LoginViewModel loginViewModel,
                                                             ViewManagerModel viewManagerModel) {
        WelcomeLoginOutputBoundary welcomeLoginOutputBoundary = new WelcomeLoginPresenter(loginViewModel, viewManagerModel);
        WelcomeLoginInputBoundary welcomeLoginInputBoundary = new WelcomeLoginInteractor(welcomeLoginOutputBoundary);

        return new WelcomeLoginController(welcomeLoginInputBoundary);
    }
}
