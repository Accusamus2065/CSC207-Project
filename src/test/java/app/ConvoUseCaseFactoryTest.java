package app;

import data_access.InMemoryConversationDataAccessObject;
import interface_adapter.ViewManagerModel;
import interface_adapter.chat.refresh.ConversationRefreshViewModel;
import interface_adapter.chatbot.DialogflowViewModel;
import interface_adapter.choose_patient.ChoosePatientViewModel;
import interface_adapter.login.LoginViewModel;
import org.junit.Test;
import use_case.chat.ConversationUserDataAccessInterface;
import view.ConversationView;

import static org.junit.Assert.*;

public class ConvoUseCaseFactoryTest {
    @Test
    public void executeUseCase() {
        ViewManagerModel viewManagerModel = new ViewManagerModel();
        LoginViewModel loginViewModel = new LoginViewModel();
        ConversationRefreshViewModel refreshViewModel = new ConversationRefreshViewModel();
        DialogflowViewModel dialogflowViewModel = new DialogflowViewModel();
        ConversationUserDataAccessInterface dataAccessObject = new InMemoryConversationDataAccessObject();
        ChoosePatientViewModel choosePatientViewModel = new ChoosePatientViewModel();

        ConversationView conversationView = ConvoUseCaseFactory.create(
                viewManagerModel,
                loginViewModel,
                refreshViewModel,
                dialogflowViewModel,
                choosePatientViewModel,
                dataAccessObject
        );
        assertNotNull(conversationView);
        assertEquals(refreshViewModel.getViewName(), conversationView.viewName);
    }
}