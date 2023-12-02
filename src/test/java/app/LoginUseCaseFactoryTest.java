package app;

import data_access.InMemoryUserDataAccessObject;
import interface_adapter.ViewManagerModel;
import interface_adapter.chat.ConversationViewModel;
import interface_adapter.choose_patient.ChoosePatientViewModel;
import interface_adapter.login.LoginViewModel;
import interface_adapter.welcome.WelcomeViewModel;
import org.junit.Test;
import view.LoginView;

import static org.junit.Assert.*;

public class LoginUseCaseFactoryTest {
    @Test
    public void createUseCaseTest() {
        ViewManagerModel viewManagerModel = new ViewManagerModel();
        WelcomeViewModel welcomeViewModel = new WelcomeViewModel();
        ConversationViewModel conversationViewModel = new ConversationViewModel();
        ChoosePatientViewModel choosePatientViewModel = new ChoosePatientViewModel();
        LoginViewModel loginViewModel = new LoginViewModel();
        InMemoryUserDataAccessObject dataAccessObject = new InMemoryUserDataAccessObject();

        LoginView loginView = LoginUseCaseFactory.create(
                viewManagerModel,
                welcomeViewModel,
                conversationViewModel,
                choosePatientViewModel,
                loginViewModel,
                dataAccessObject
        );
        assertEquals(loginViewModel.getViewName(), loginView.viewName);
    }
}