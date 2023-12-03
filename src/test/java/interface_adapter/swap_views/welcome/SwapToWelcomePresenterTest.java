package interface_adapter.swap_views.welcome;

import interface_adapter.ViewManagerModel;
import interface_adapter.login.LoginViewModel;
import interface_adapter.signup.SignupViewModel;
import interface_adapter.welcome.WelcomeViewModel;
import org.junit.Test;
import use_case.swap_views.welcome.SwapToWelcomeData;

import static org.junit.Assert.*;

public class SwapToWelcomePresenterTest {
    @Test
    public void swapSignupToWelcomeTest() {
        ViewManagerModel viewManagerModel = new ViewManagerModel();
        SignupViewModel signupViewModel = new SignupViewModel();
        WelcomeViewModel welcomeViewModel = new WelcomeViewModel();
        LoginViewModel loginViewModel = new LoginViewModel();
        viewManagerModel.setActiveView(signupViewModel.getViewName());

        SwapToWelcomePresenter swaptoWelcomePresenter = new SwapToWelcomePresenter(viewManagerModel, welcomeViewModel, loginViewModel, signupViewModel);
        SwapToWelcomeData data = new SwapToWelcomeData(signupViewModel.getViewName());
        swaptoWelcomePresenter.execute(data);

        assertEquals(viewManagerModel.getActiveView(), welcomeViewModel.getViewName());
    }

    @Test
    public void swapLoginToWelcomeTest() {
        ViewManagerModel viewManagerModel = new ViewManagerModel();
        SignupViewModel signupViewModel = new SignupViewModel();
        LoginViewModel loginViewModel = new LoginViewModel();
        WelcomeViewModel welcomeViewModel = new WelcomeViewModel();
        viewManagerModel.setActiveView(loginViewModel.getViewName());

        SwapToWelcomePresenter swaptoWelcomePresenter = new SwapToWelcomePresenter(viewManagerModel, welcomeViewModel, loginViewModel, signupViewModel);
        SwapToWelcomeData data = new SwapToWelcomeData(loginViewModel.getViewName());
        swaptoWelcomePresenter.execute(data);

        assertEquals(viewManagerModel.getActiveView(), welcomeViewModel.getViewName());
    }
}