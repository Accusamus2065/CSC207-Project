package app;

import data_access.InMemoryUserDataAccessObject;
import interface_adapter.ViewManagerModel;
import interface_adapter.choose_patient.ChoosePatientViewModel;
import interface_adapter.login.LoginViewModel;
import interface_adapter.signup.SignupViewModel;
import interface_adapter.welcome.WelcomeViewModel;
import view.SignupView;
import org.junit.Test;

import static org.junit.Assert.*;

public class SignupUseCaseFactoryTest {
    @Test
    public void createUseCaseTest() {
        ViewManagerModel viewManagerModel = new ViewManagerModel();
        WelcomeViewModel welcomeViewModel = new WelcomeViewModel();
        SignupViewModel signupViewModel = new SignupViewModel();
        LoginViewModel loginViewModel = new LoginViewModel();
        ChoosePatientViewModel choosePatientViewModel = new ChoosePatientViewModel();
        InMemoryUserDataAccessObject dataAccessObject = new InMemoryUserDataAccessObject();
        SignupView signupView = SignupUseCaseFactory.create(viewManagerModel,
                welcomeViewModel,
                signupViewModel,
                loginViewModel,
                choosePatientViewModel,
                dataAccessObject);

        assertEquals(signupViewModel.getViewName(), signupView.viewName);
    }
}