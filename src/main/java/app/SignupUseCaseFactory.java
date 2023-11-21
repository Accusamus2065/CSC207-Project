package app;

import com.mongodb.MongoException;
import entity.people.DoctorUserFactory;
import entity.people.PatientUserFactory;
import interface_adapter.ViewManagerModel;
import interface_adapter.login.LoginViewModel;
import interface_adapter.signup.SignupController;
import interface_adapter.signup.SignupPresenter;
import interface_adapter.signup.SignupViewModel;
import interface_adapter.swap_views.welcome.SwapToWelcomeController;
import interface_adapter.swap_views.welcome.SwapToWelcomePresenter;
import interface_adapter.welcome.WelcomeViewModel;
import use_case.signup.*;
import use_case.swap_views.welcome.SwapToWelcomeInputBoundary;
import use_case.swap_views.welcome.SwapToWelcomeInteractor;
import use_case.swap_views.welcome.SwapToWelcomeOutputBoundary;
import view.SignupView;

import javax.swing.*;

public class SignupUseCaseFactory {
    private SignupUseCaseFactory() {
    }

    public static SignupView create(
            ViewManagerModel viewManagerModel,
            WelcomeViewModel welcomeViewModel,
            SignupViewModel signupViewModel,
            LoginViewModel loginViewModel,
            SignupUserDataAccessInterface userDataAccessObject
    ) {
        try {
            SignupController signupController = createUserSignupUseCase(
                    viewManagerModel, signupViewModel, loginViewModel, userDataAccessObject
            );
            SwapToWelcomeController swaptoWelcomeController = createSwapViewsUseCase(viewManagerModel, welcomeViewModel);
            return new SignupView(signupViewModel, signupController, swaptoWelcomeController);
        } catch (MongoException e) {
            JOptionPane.showMessageDialog(null, "Could not read user data");
        }
        return null;
    }

    public static SignupController createUserSignupUseCase(
            ViewManagerModel viewManagerModel,
            SignupViewModel signupViewModel,
            LoginViewModel loginViewModel,
            SignupUserDataAccessInterface userDataAccessObject
    ) throws MongoException {
        SignupOutputBoundary signupOutputBoundary = new SignupPresenter(viewManagerModel, signupViewModel, loginViewModel);
        DoctorUserFactory doctorUserFactory = new DoctorUserFactory();
        PatientUserFactory patientUserFactory = new PatientUserFactory();
        SignupInputBoundary userSignupInteractor = new SignupInteractor(
                userDataAccessObject, signupOutputBoundary, doctorUserFactory, patientUserFactory
        );
        return new SignupController(userSignupInteractor);
    }

    public static SwapToWelcomeController createSwapViewsUseCase(
            ViewManagerModel viewManagerModel,
            WelcomeViewModel welcomeViewModel
    ) {
        SwapToWelcomeOutputBoundary swaptoWelcomeOutputBoundary = new SwapToWelcomePresenter(viewManagerModel, welcomeViewModel);
        SwapToWelcomeInputBoundary swaptoWelcomeInteractor = new SwapToWelcomeInteractor(swaptoWelcomeOutputBoundary);
        return new SwapToWelcomeController(swaptoWelcomeInteractor);
    }
}
