package app;

import data_access.InMemoryUserDataAccessObject;
import interface_adapter.ViewManagerModel;
import interface_adapter.chatbot.DialogflowViewModel;
import interface_adapter.choose_patient.ChoosePatientViewModel;
import interface_adapter.login.LoginViewModel;
import interface_adapter.signup.SignupViewModel;
import interface_adapter.welcome.WelcomeViewModel;
import org.junit.Test;
import view.LoginView;

import static org.junit.Assert.*;

public class LoginUseCaseFactoryTest {
    @Test
    public void createUseCaseTest() {
        ViewManagerModel viewManagerModel = new ViewManagerModel();
        WelcomeViewModel welcomeViewModel = new WelcomeViewModel();
        DialogflowViewModel dialogflowViewModel = new DialogflowViewModel();
        ChoosePatientViewModel choosePatientViewModel = new ChoosePatientViewModel();
        LoginViewModel loginViewModel = new LoginViewModel();
        SignupViewModel signupViewModel = new SignupViewModel();
        InMemoryUserDataAccessObject dataAccessObject = new InMemoryUserDataAccessObject();

        LoginView loginView = LoginUseCaseFactory.create(
                viewManagerModel,
                welcomeViewModel,
                dialogflowViewModel,
                choosePatientViewModel,
                loginViewModel,
                signupViewModel,
                dataAccessObject
        );
        assertEquals(loginViewModel.getViewName(), loginView.viewName);
    }
}