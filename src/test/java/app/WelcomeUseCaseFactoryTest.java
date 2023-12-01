package app;

import interface_adapter.ViewManagerModel;
import interface_adapter.login.LoginViewModel;
import interface_adapter.signup.SignupViewModel;
import interface_adapter.welcome.WelcomeViewModel;
import org.junit.Test;
import view.WelcomeView;

import static org.junit.Assert.*;

public class WelcomeUseCaseFactoryTest {
    @Test
    public void executeUseCaseTest() {
        ViewManagerModel viewManagerModel = new ViewManagerModel();
        WelcomeViewModel welcomeViewModel = new WelcomeViewModel();
        SignupViewModel signupViewModel = new SignupViewModel();
        LoginViewModel loginViewModel = new LoginViewModel();
        WelcomeView welcomeView = WelcomeUseCaseFactory.create(
                welcomeViewModel,
                signupViewModel,
                loginViewModel,
                viewManagerModel);

        assertEquals(welcomeViewModel.getViewName(), welcomeView.viewName);
    }
}