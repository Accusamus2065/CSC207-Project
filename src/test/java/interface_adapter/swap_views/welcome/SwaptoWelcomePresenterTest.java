package interface_adapter.swap_views.welcome;

import interface_adapter.ViewManagerModel;
import interface_adapter.login.LoginViewModel;
import interface_adapter.signup.SignupViewModel;
import interface_adapter.welcome.WelcomeViewModel;
import org.junit.Test;

import static org.junit.Assert.*;

public class SwaptoWelcomePresenterTest {
    @Test
    public void swapSignupToWelcomeTest() {
        ViewManagerModel viewManagerModel = new ViewManagerModel();
        SignupViewModel signupViewModel = new SignupViewModel();
        WelcomeViewModel welcomeViewModel = new WelcomeViewModel();
        viewManagerModel.setActiveView(signupViewModel.getViewName());

        SwaptoWelcomePresenter swaptoWelcomePresenter = new SwaptoWelcomePresenter(viewManagerModel, welcomeViewModel);
        swaptoWelcomePresenter.execute();

        assertEquals(viewManagerModel.getActiveView(), welcomeViewModel.getViewName());
    }

    @Test
    public void swapLoginToWelcomeTest() {
        ViewManagerModel viewManagerModel = new ViewManagerModel();
        LoginViewModel loginViewModel = new LoginViewModel();
        WelcomeViewModel welcomeViewModel = new WelcomeViewModel();
        viewManagerModel.setActiveView(loginViewModel.getViewName());

        SwaptoWelcomePresenter swaptoWelcomePresenter = new SwaptoWelcomePresenter(viewManagerModel, welcomeViewModel);
        swaptoWelcomePresenter.execute();

        assertEquals(viewManagerModel.getActiveView(), welcomeViewModel.getViewName());
    }
}