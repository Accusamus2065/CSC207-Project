package app;

import data_access.InMemoryConversationDataAccessObject;
import interface_adapter.ViewManagerModel;
import interface_adapter.chat.refresh.ConversationRefreshViewModel;
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
        ConversationUserDataAccessInterface dataAccessObject = new InMemoryConversationDataAccessObject();

        ConversationView conversationView = ConvoUseCaseFactory.create(
                viewManagerModel,
                loginViewModel,
                refreshViewModel,
                dataAccessObject
        );
        assertNotNull(conversationView);
        assertEquals(refreshViewModel.getViewName(), conversationView.viewName);
    }
}