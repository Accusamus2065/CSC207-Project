package interface_adapter.welcome.login;

import interface_adapter.ViewManagerModel;
import interface_adapter.login.LoginViewModel;
import org.junit.Test;
import use_case.welcome.WelcomeOutputData;

import static org.junit.Assert.*;

public class WelcomeLoginPresenterTest {
    @Test
    public void WelcomeLoginIsDoctorTest() {
        ViewManagerModel viewManagerModel = new ViewManagerModel();
        LoginViewModel loginViewModel = new LoginViewModel();

        WelcomeLoginPresenter loginPresenter = new WelcomeLoginPresenter(loginViewModel, viewManagerModel);
        WelcomeOutputData welcomeOutputData = new WelcomeOutputData(true);
        loginPresenter.swapViews(welcomeOutputData);

        assertEquals(loginViewModel.getViewName(), viewManagerModel.getActiveView());
        assertTrue(loginViewModel.getState().isDoctor());
    }

    @Test
    public void WelcomeLoginIsPatientTest() {
        ViewManagerModel viewManagerModel = new ViewManagerModel();
        LoginViewModel loginViewModel = new LoginViewModel();

        WelcomeLoginPresenter loginPresenter = new WelcomeLoginPresenter(loginViewModel, viewManagerModel);
        WelcomeOutputData welcomeOutputData = new WelcomeOutputData(false);
        loginPresenter.swapViews(welcomeOutputData);

        assertEquals(loginViewModel.getViewName(), viewManagerModel.getActiveView());
        assertTrue(loginViewModel.getState().isDoctor());
    }
}