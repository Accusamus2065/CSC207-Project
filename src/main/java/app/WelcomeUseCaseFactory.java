package app;

import interface_adapter.ViewManagerModel;
import interface_adapter.welcome.WelcomeViewModel;
import interface_adapter.welcome.login.WelcomeLoginController;
import interface_adapter.welcome.login.WelcomeLoginPresenter;
import interface_adapter.welcome.signup.WelcomeSignupController;
import interface_adapter.welcome.signup.WelcomeSignupPresenter;
import use_case.welcome.login.WelcomeLoginInputBoundary;
import use_case.welcome.login.WelcomeLoginInteractor;
import use_case.welcome.login.WelcomeLoginOutputBoundary;
import use_case.welcome.signup.WelcomeSignupInputBoundary;
import use_case.welcome.signup.WelcomeSignupInteractor;
import use_case.welcome.signup.WelcomeSignupOutputBoundary;
import view.ViewManager;
import view.WelcomeView;

public class WelcomeUseCaseFactory {

    /** Prevent instantiation. */
    private WelcomeUseCaseFactory() {}

    public static WelcomeView create(WelcomeViewModel welcomeViewModel) {
        WelcomeSignupController signupController = createSignupUseCase();
        WelcomeLoginController loginController = createLoginUseCase();
        return new WelcomeView(welcomeViewModel, signupController, loginController);
    }

    private static WelcomeSignupController createSignupUseCase() {
        WelcomeSignupOutputBoundary welcomeSignupOutputBoundary = new WelcomeSignupPresenter();
        WelcomeSignupInputBoundary welcomeSignupInputBoundary = new WelcomeSignupInteractor(welcomeSignupOutputBoundary);

        return new WelcomeSignupController(welcomeSignupInputBoundary);
    }

    private static WelcomeLoginController createLoginUseCase() {
        WelcomeLoginOutputBoundary welcomeLoginOutputBoundary = new WelcomeLoginPresenter();
        WelcomeLoginInputBoundary welcomeLoginInputBoundary = new WelcomeLoginInteractor(welcomeLoginOutputBoundary);

        return new WelcomeLoginController(welcomeLoginInputBoundary);
    }
}
