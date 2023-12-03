package app;

import entity.people.DoctorUserFactory;
import entity.people.PatientUserFactory;
import interface_adapter.ViewManagerModel;
import interface_adapter.choose_patient.ChoosePatientViewModel;
import interface_adapter.login.LoginViewModel;
import interface_adapter.signup.SignupController;
import interface_adapter.signup.SignupPresenter;
import interface_adapter.signup.SignupViewModel;
import interface_adapter.swap_views.welcome.SwapToWelcomeController;
import interface_adapter.swap_views.welcome.SwapToWelcomePresenter;
import interface_adapter.welcome.WelcomeViewModel;
import use_case.signup.SignupInputBoundary;
import use_case.signup.SignupInteractor;
import use_case.signup.SignupOutputBoundary;
import use_case.signup.SignupUserDataAccessInterface;
import use_case.swap_views.welcome.SwapToWelcomeInputBoundary;
import use_case.swap_views.welcome.SwapToWelcomeInteractor;
import use_case.swap_views.welcome.SwapToWelcomeOutputBoundary;
import view.SignupView;

public class SignupUseCaseFactory {
    private SignupUseCaseFactory() {
    }

    public static SignupView create(
            ViewManagerModel viewManagerModel,
            WelcomeViewModel welcomeViewModel,
            SignupViewModel signupViewModel,
            LoginViewModel loginViewModel,
            ChoosePatientViewModel choosePatientViewModel,
            SignupUserDataAccessInterface userDataAccessObject
    ) {
        SignupController signupController = createUserSignupUseCase(
                viewManagerModel, signupViewModel, loginViewModel, userDataAccessObject
        );
        SwapToWelcomeController swaptoWelcomeController = createSwapViewsUseCase(viewManagerModel, welcomeViewModel, loginViewModel, signupViewModel, choosePatientViewModel);
        return new SignupView(signupViewModel, signupController, swaptoWelcomeController);
    }

    public static SignupController createUserSignupUseCase(
            ViewManagerModel viewManagerModel,
            SignupViewModel signupViewModel,
            LoginViewModel loginViewModel,
            SignupUserDataAccessInterface userDataAccessObject
    ) {
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
            WelcomeViewModel welcomeViewModel,
            LoginViewModel loginViewModel,
            SignupViewModel signupViewModel,
            ChoosePatientViewModel choosePatientViewModel
    ) {
        SwapToWelcomeOutputBoundary swapToWelcomeOutputBoundary = new SwapToWelcomePresenter(viewManagerModel, welcomeViewModel, loginViewModel, signupViewModel, choosePatientViewModel);
        SwapToWelcomeInputBoundary swapToWelcomeInteractor = new SwapToWelcomeInteractor(swapToWelcomeOutputBoundary);
        return new SwapToWelcomeController(swapToWelcomeInteractor);
    }
}
