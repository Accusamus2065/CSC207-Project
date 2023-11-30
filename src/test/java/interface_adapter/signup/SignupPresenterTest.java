package interface_adapter.signup;

import interface_adapter.ViewManagerModel;
import interface_adapter.login.LoginState;
import interface_adapter.login.LoginViewModel;
import org.junit.Test;
import use_case.signup.SignupOutputData;

import static org.junit.Assert.*;

public class SignupPresenterTest {
    private static final String USERNAME = "SignupPresenterTestUsername";
    private static final String ERROR = "SignupPresenterTestError";

    @Test
    public void successViewTest() {
        ViewManagerModel viewManagerModel = new ViewManagerModel();
        SignupViewModel signupViewModel = new SignupViewModel();
        LoginViewModel loginViewModel = new LoginViewModel();
        viewManagerModel.setActiveView(signupViewModel.getViewName());
        SignupOutputData outputData = new SignupOutputData(USERNAME, false);

        SignupPresenter signupPresenter = new SignupPresenter(viewManagerModel, signupViewModel, loginViewModel);
        signupPresenter.prepareSuccessView(outputData);

        assertEquals(loginViewModel.getViewName(), viewManagerModel.getActiveView());

        LoginState loginState = loginViewModel.getState();
        assertEquals(USERNAME, loginState.getUsername());
    }

    @Test
    public void failViewTest() {
        ViewManagerModel viewManagerModel = new ViewManagerModel();
        SignupViewModel signupViewModel = new SignupViewModel();
        LoginViewModel loginViewModel = new LoginViewModel();
        LoginState loginState = loginViewModel.getState();
        viewManagerModel.setActiveView(signupViewModel.getViewName());

        SignupPresenter signupPresenter = new SignupPresenter(viewManagerModel, signupViewModel, loginViewModel);
        signupPresenter.prepareFailView(ERROR);

        assertEquals(signupViewModel.getViewName(), viewManagerModel.getActiveView());
        assertEquals(ERROR, signupViewModel.getState().getError());
        assertEquals(loginState, loginViewModel.getState());
    }
}