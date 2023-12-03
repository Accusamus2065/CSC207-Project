package app;

import data_access.InMemoryUserDataAccessObject;
import interface_adapter.ViewManagerModel;
import interface_adapter.chat.refresh.ConversationRefreshViewModel;
import interface_adapter.choose_patient.ChoosePatientViewModel;
import interface_adapter.login.LoginViewModel;
import interface_adapter.update.doctor.DoctorUpdateViewModel;
import org.junit.Test;
import use_case.choose_patient.ChoosePatientUserDataAccessInterface;
import view.ListOfPatientsView;

import static org.junit.Assert.*;

public class ChoosePatientUseCaseFactoryTest {
    @Test
    public void executeUseCase() {
        ViewManagerModel viewManagerModel = new ViewManagerModel();
        ConversationRefreshViewModel conversationViewModel = new ConversationRefreshViewModel();
        DoctorUpdateViewModel doctorUpdateViewModel = new DoctorUpdateViewModel();
        LoginViewModel loginViewModel = new LoginViewModel();
        ChoosePatientViewModel choosePatientViewModel = new ChoosePatientViewModel();
        ChoosePatientUserDataAccessInterface dataAccessObject = new InMemoryUserDataAccessObject();

        ListOfPatientsView listOfPatientsView = ChoosePatientUseCaseFactory.create(
                viewManagerModel,
                conversationViewModel,
                doctorUpdateViewModel,
                choosePatientViewModel,
                loginViewModel,
                dataAccessObject
        );
        assertEquals(choosePatientViewModel.getViewName(), listOfPatientsView.viewName);
    }
}