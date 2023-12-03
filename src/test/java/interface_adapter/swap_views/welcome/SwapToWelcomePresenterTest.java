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

        SignupState blankSignupState = new SignupState();
        SignupState signupState = signupViewModel.getState();
        assertEquals(blankSignupState.getUsername(), signupState.getUsername());
        assertEquals(blankSignupState.getPassword(), signupState.getPassword());
        assertEquals(blankSignupState.getRepeatPassword(), signupState.getRepeatPassword());
        assertEquals(blankSignupState.getError(), signupState.getError());
        assertEquals(blankSignupState.isDoctor(), signupState.isDoctor());
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
        LoginState blankLoginState = new LoginState();
        LoginState loginState = loginViewModel.getState();
        assertEquals(blankLoginState.getUsername(), loginState.getUsername());
        assertEquals(blankLoginState.getPassword(), loginState.getPassword());
        assertEquals(blankLoginState.getError(), loginState.getError());
        assertEquals(blankLoginState.isDoctor(), loginState.isDoctor());
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
        ChoosePatientState blankChooseState = new ChoosePatientState();
        ChoosePatientState choosePatientState = choosePatientViewModel.getState();
        assertEquals(blankChooseState.getPatients(), choosePatientState.getPatients());
        assertEquals(blankChooseState.getUsername(), choosePatientState.getUsername());

        LoginState blankLoginState = new LoginState();
        LoginState loginState = loginViewModel.getState();
        assertEquals(blankLoginState.getUsername(), loginState.getUsername());
        assertEquals(blankLoginState.getPassword(), loginState.getPassword());
        assertEquals(blankLoginState.getError(), loginState.getError());
        assertEquals(blankLoginState.isDoctor(), loginState.isDoctor());

        SignupState blankSignupState = new SignupState();
        SignupState signupState = signupViewModel.getState();
        assertEquals(blankSignupState.getUsername(), signupState.getUsername());
        assertEquals(blankSignupState.getPassword(), signupState.getPassword());
        assertEquals(blankSignupState.getRepeatPassword(), signupState.getRepeatPassword());
        assertEquals(blankSignupState.getError(), signupState.getError());
        assertEquals(blankSignupState.isDoctor(), signupState.isDoctor());
    }
}