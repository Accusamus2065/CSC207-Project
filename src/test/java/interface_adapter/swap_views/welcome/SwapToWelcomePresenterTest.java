package interface_adapter.swap_views.welcome;

import interface_adapter.ViewManagerModel;
import interface_adapter.choose_patient.ChoosePatientState;
import interface_adapter.choose_patient.ChoosePatientViewModel;
import interface_adapter.login.LoginState;
import interface_adapter.login.LoginViewModel;
import interface_adapter.signup.SignupState;
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
        ChoosePatientViewModel choosePatientViewModel = new ChoosePatientViewModel();
        viewManagerModel.setActiveView(signupViewModel.getViewName());

        SwapToWelcomePresenter swaptoWelcomePresenter = new SwapToWelcomePresenter(viewManagerModel, welcomeViewModel, loginViewModel, signupViewModel, choosePatientViewModel);
        SwapToWelcomeData data = new SwapToWelcomeData(signupViewModel.getViewName());
        swaptoWelcomePresenter.execute(data);

        assertEquals(viewManagerModel.getActiveView(), welcomeViewModel.getViewName());
        assertEquals(new SignupState(), signupViewModel.getState());
    }

    @Test
    public void swapLoginToWelcomeTest() {
        ViewManagerModel viewManagerModel = new ViewManagerModel();
        SignupViewModel signupViewModel = new SignupViewModel();
        LoginViewModel loginViewModel = new LoginViewModel();
        WelcomeViewModel welcomeViewModel = new WelcomeViewModel();
        ChoosePatientViewModel choosePatientViewModel = new ChoosePatientViewModel();
        viewManagerModel.setActiveView(loginViewModel.getViewName());

        SwapToWelcomePresenter swaptoWelcomePresenter = new SwapToWelcomePresenter(viewManagerModel, welcomeViewModel, loginViewModel, signupViewModel, choosePatientViewModel);
        SwapToWelcomeData data = new SwapToWelcomeData(loginViewModel.getViewName());
        swaptoWelcomePresenter.execute(data);

        assertEquals(viewManagerModel.getActiveView(), welcomeViewModel.getViewName());
        assertEquals(new LoginState(), loginViewModel.getState());
    }

    @Test
    public void swapListOfPatientsToWelcomeTest() {
        ViewManagerModel viewManagerModel = new ViewManagerModel();
        SignupViewModel signupViewModel = new SignupViewModel();
        LoginViewModel loginViewModel = new LoginViewModel();
        WelcomeViewModel welcomeViewModel = new WelcomeViewModel();
        ChoosePatientViewModel choosePatientViewModel = new ChoosePatientViewModel();
        viewManagerModel.setActiveView(choosePatientViewModel.getViewName());

        SwapToWelcomePresenter swaptoWelcomePresenter = new SwapToWelcomePresenter(viewManagerModel, welcomeViewModel, loginViewModel, signupViewModel, choosePatientViewModel);
        SwapToWelcomeData data = new SwapToWelcomeData(choosePatientViewModel.getViewName());
        swaptoWelcomePresenter.execute(data);

        assertEquals(viewManagerModel.getActiveView(), welcomeViewModel.getViewName());
        assertEquals(new ChoosePatientState(), choosePatientViewModel.getState());
        assertEquals(new LoginState(), loginViewModel.getState());
        assertEquals(new SignupState(), signupViewModel.getState());
    }
}