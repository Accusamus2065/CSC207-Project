package interface_adapter.login;

import interface_adapter.ViewManagerModel;
import interface_adapter.chat.ConversationState;
import interface_adapter.chat.ConversationViewModel;
import interface_adapter.choosepatient.ChoosePatientState;
import interface_adapter.choosepatient.ChoosePatientViewModel;
import org.junit.Test;
import use_case.login.LoginOutputData;

import static org.junit.Assert.*;

public class LoginPresenterTest {
    private static final String USERNAME = "LoginPresenterTestUsername";
    private static final String ERROR = "LoginPresenterTestError";
    @Test
    public void doctorLoginTest() {
        ViewManagerModel viewManagerModel = new ViewManagerModel();
        LoginViewModel loginViewModel = new LoginViewModel();
        LoginState loginState = new LoginState();
        loginState.setDoctor(true);
        loginViewModel.setState(loginState);
        ConversationViewModel conversationViewModel = new ConversationViewModel();
        ChoosePatientViewModel choosePatientViewModel = new ChoosePatientViewModel();

        LoginPresenter loginPresenter = new LoginPresenter(viewManagerModel,
                conversationViewModel,
                choosePatientViewModel,
                loginViewModel);
        LoginOutputData outputData = new LoginOutputData(USERNAME, false);
        loginPresenter.prepareSuccessView(outputData);

        assertEquals(choosePatientViewModel.getViewName(), viewManagerModel.getActiveView());
        assertEquals(USERNAME, choosePatientViewModel.getState().getUsername());
        assertNull(loginViewModel.getState().getError());
    }

    @Test
    public void patientLoginTest() {
        ViewManagerModel viewManagerModel = new ViewManagerModel();
        LoginViewModel loginViewModel = new LoginViewModel();
        LoginState loginState = new LoginState();
        loginState.setDoctor(false);
        loginViewModel.setState(loginState);
        ConversationViewModel conversationViewModel = new ConversationViewModel();
        ChoosePatientViewModel choosePatientViewModel = new ChoosePatientViewModel();

        LoginPresenter loginPresenter = new LoginPresenter(viewManagerModel,
                conversationViewModel,
                choosePatientViewModel,
                loginViewModel);
        LoginOutputData outputData = new LoginOutputData(USERNAME, false);
        loginPresenter.prepareSuccessView(outputData);

        assertEquals(conversationViewModel.getViewName(), viewManagerModel.getActiveView());
        assertNull(loginViewModel.getState().getError());
    }

    @Test
    public void failLoginTest() {
        ViewManagerModel viewManagerModel = new ViewManagerModel();
        LoginViewModel loginViewModel = new LoginViewModel();
        ConversationViewModel conversationViewModel = new ConversationViewModel();
        ConversationState conversationState = conversationViewModel.getState();
        ChoosePatientViewModel choosePatientViewModel = new ChoosePatientViewModel();
        ChoosePatientState choosePatientState = choosePatientViewModel.getState();

        LoginPresenter loginPresenter = new LoginPresenter(viewManagerModel,
                conversationViewModel,
                choosePatientViewModel,
                loginViewModel);
        loginPresenter.prepareFailView(ERROR);

        assertEquals(loginViewModel.getViewName(), viewManagerModel.getActiveView());
        assertEquals(ERROR, loginViewModel.getState().getError());
        assertEquals(conversationState, conversationViewModel.getState());
        assertEquals(choosePatientState, choosePatientViewModel.getState());
    }
}