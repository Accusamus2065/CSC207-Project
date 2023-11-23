package app;

import com.mongodb.MongoException;

import interface_adapter.ViewManagerModel;
import interface_adapter.chat.ConversationViewModel;
import interface_adapter.choosepatient.ChoosePatientViewModel;
import interface_adapter.login.LoginController;
import interface_adapter.login.LoginPresenter;
import interface_adapter.login.LoginViewModel;

import interface_adapter.swap_views.welcome.SwapToWelcomeController;
import interface_adapter.swap_views.welcome.SwapToWelcomePresenter;
import interface_adapter.welcome.WelcomeViewModel;
import use_case.login.LoginInputBoundary;
import use_case.login.LoginInteractor;
import use_case.login.LoginOutputBoundary;
import use_case.login.LoginUserDataAccessInterface;

import use_case.swap_views.welcome.SwapToWelcomeInputBoundary;
import use_case.swap_views.welcome.SwapToWelcomeInteractor;
import use_case.swap_views.welcome.SwapToWelcomeOutputBoundary;
import view.LoginView;


import javax.swing.*;


public class LoginUseCaseFactory {

    /** Prevent instantiation. */
    private LoginUseCaseFactory() {}

    public static LoginView create(
            ViewManagerModel viewManagerModel,
            WelcomeViewModel welcomeViewModel,
            ConversationViewModel conversationViewModel,
            ChoosePatientViewModel choosePatientViewModel,
            LoginViewModel loginViewModel,
            LoginUserDataAccessInterface userDataAccessObject) {

        try {
            LoginController loginController =
                    createLoginUseCase(
                            viewManagerModel,
                            conversationViewModel,
                            choosePatientViewModel,
                            loginViewModel,
                            userDataAccessObject);
            SwapToWelcomeController swaptoWelcomeController = createSwapViewsUseCase(viewManagerModel, welcomeViewModel);
            return new LoginView(loginViewModel, loginController, swaptoWelcomeController);
        } catch (MongoException e) {
            JOptionPane.showMessageDialog(null, "Could not open user data file.");
        }

        return null;
    }

    private static LoginController createLoginUseCase(
            ViewManagerModel viewManagerModel,
            ConversationViewModel conversationViewModel,
            ChoosePatientViewModel choosePatientViewModel,
            LoginViewModel loginViewModel,
            LoginUserDataAccessInterface userDataAccessObject) throws MongoException {
            LoginOutputBoundary loginOutputBoundary =
                    new LoginPresenter(viewManagerModel, conversationViewModel, choosePatientViewModel, loginViewModel);
            LoginInputBoundary userLoginInteractor = new LoginInteractor(
                    userDataAccessObject, loginOutputBoundary);
            return new LoginController(userLoginInteractor);
        }


    public static SwapToWelcomeController createSwapViewsUseCase(
            ViewManagerModel viewManagerModel,
            WelcomeViewModel welcomeViewModel
    ) {
        SwapToWelcomeOutputBoundary swapToWelcomeOutputBoundary = new SwapToWelcomePresenter(viewManagerModel, welcomeViewModel);
        SwapToWelcomeInputBoundary swapToWelcomeInteractor = new SwapToWelcomeInteractor(swapToWelcomeOutputBoundary);
        return new SwapToWelcomeController(swapToWelcomeInteractor);
    }
}