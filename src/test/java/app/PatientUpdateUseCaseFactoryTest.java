package app;

import data_access.InMemoryUserDataAccessObject;
import interface_adapter.ViewManagerModel;
import interface_adapter.chatbot.DialogflowViewModel;
import interface_adapter.login.LoginViewModel;
import interface_adapter.update.patient.PatientUpdateViewModel;
import org.junit.Test;
import use_case.update.patient.PatientUpdateUserDataAccessInterface;
import view.PatientUpdateView;

import static org.junit.Assert.*;

public class PatientUpdateUseCaseFactoryTest {
    @Test
    public void executeUseCase() {
        PatientUpdateUserDataAccessInterface dataAccessObject = new InMemoryUserDataAccessObject();
        ViewManagerModel viewManagerModel = new ViewManagerModel();
        PatientUpdateViewModel patientUpdateViewModel = new PatientUpdateViewModel();
        DialogflowViewModel dialogflowViewModel = new DialogflowViewModel();
        LoginViewModel loginViewModel = new LoginViewModel();
        PatientUpdateView patientUpdateView = PatientUpdateUseCaseFactory.create(
                dataAccessObject,
                viewManagerModel,
                patientUpdateViewModel,
                dialogflowViewModel,
                loginViewModel);

        assertEquals(patientUpdateViewModel.getViewName(), patientUpdateView.viewName);
    }
}