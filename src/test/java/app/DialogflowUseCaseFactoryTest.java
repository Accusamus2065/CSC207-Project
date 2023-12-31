package app;

import data_access.InMemoryUserDataAccessObject;
import interface_adapter.ViewManagerModel;
import interface_adapter.ViewModel;
import interface_adapter.chat.refresh.ConversationRefreshViewModel;
import interface_adapter.chatbot.DialogflowViewModel;
import interface_adapter.login.LoginViewModel;
import interface_adapter.update.patient.PatientUpdateViewModel;
import view.DialogflowView;
import org.junit.Test;

import static org.junit.Assert.*;

public class DialogflowUseCaseFactoryTest {
    @Test
    public void executeUseCase() {
        ViewManagerModel viewManagerModel = new ViewManagerModel();
        LoginViewModel loginViewModel = new LoginViewModel();
        DialogflowViewModel dialogflowViewModel = new DialogflowViewModel();
        ConversationRefreshViewModel conversationRefreshViewModel = new ConversationRefreshViewModel();
        PatientUpdateViewModel patientUpdateViewModel = new PatientUpdateViewModel();
        InMemoryUserDataAccessObject dataAccessObject = new InMemoryUserDataAccessObject();
        DialogflowView dialogflowView = DialogflowUseCaseFactory.create(
                viewManagerModel,
                loginViewModel,
                dialogflowViewModel,
                conversationRefreshViewModel,
                patientUpdateViewModel,
                dataAccessObject);

        assertEquals(dialogflowViewModel.getViewName(), dialogflowView.viewName);
    }
}