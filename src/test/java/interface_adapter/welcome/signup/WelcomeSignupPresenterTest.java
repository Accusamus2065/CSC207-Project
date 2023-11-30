package interface_adapter.welcome.signup;

import interface_adapter.ViewManagerModel;
import interface_adapter.signup.SignupViewModel;
import org.junit.Test;
import use_case.welcome.WelcomeOutputData;

import static org.junit.Assert.*;

public class WelcomeSignupPresenterTest {
    @Test
    public void WelcomeSignupIsDoctorTest() {
        ViewManagerModel viewManagerModel = new ViewManagerModel();
        SignupViewModel signupViewModel = new SignupViewModel();

        WelcomeSignupPresenter signupPresenter = new WelcomeSignupPresenter(signupViewModel, viewManagerModel);
        WelcomeOutputData welcomeOutputData = new WelcomeOutputData(true);
        signupPresenter.swapViews(welcomeOutputData);

        assertEquals(signupViewModel.getViewName(), viewManagerModel.getActiveView());
        assertTrue(signupViewModel.getState().isDoctor());
    }

    @Test
    public void WelcomeSignupIsPatientTest() {
        ViewManagerModel viewManagerModel = new ViewManagerModel();
        SignupViewModel signupViewModel = new SignupViewModel();

        WelcomeSignupPresenter signupPresenter = new WelcomeSignupPresenter(signupViewModel, viewManagerModel);
        WelcomeOutputData welcomeOutputData = new WelcomeOutputData(false);
        signupPresenter.swapViews(welcomeOutputData);

        assertEquals(signupViewModel.getViewName(), viewManagerModel.getActiveView());
        assertFalse(signupViewModel.getState().isDoctor());
    }
}