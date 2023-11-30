package interface_adapter.update.patient;

import interface_adapter.ViewManagerModel;
import interface_adapter.login.LoginState;
import interface_adapter.login.LoginViewModel;
import org.junit.Test;
import use_case.update.patient.PatientUpdateOutputData;

import static org.junit.Assert.*;

public class PatientUpdatePresenterTest {
    private static final String USERNAME = "patientUpdatePresenterTestUsername";
    private static final String ERROR = "patientUpdatePresenterTestError";
    @Test
    public void successUpdateTest() {
        ViewManagerModel viewManagerModel = new ViewManagerModel();
        LoginViewModel loginViewModel = new LoginViewModel();
        PatientUpdateViewModel patientUpdateViewModel = new PatientUpdateViewModel();

        PatientUpdatePresenter updatePresenter = new PatientUpdatePresenter(loginViewModel,
                patientUpdateViewModel,
                viewManagerModel);
        PatientUpdateOutputData outputData = new PatientUpdateOutputData(USERNAME, false);
        updatePresenter.prepareSuccessView(outputData);

        assertEquals(loginViewModel.getViewName(), viewManagerModel.getActiveView());
        assertEquals(USERNAME, loginViewModel.getState().getUsername());
    }

    @Test
    public void failUpdateTest() {
        ViewManagerModel viewManagerModel = new ViewManagerModel();
        LoginViewModel loginViewModel = new LoginViewModel();
        LoginState loginState = loginViewModel.getState();
        PatientUpdateViewModel patientUpdateViewModel = new PatientUpdateViewModel();
        viewManagerModel.setActiveView(patientUpdateViewModel.getViewName());

        PatientUpdatePresenter updatePresenter = new PatientUpdatePresenter(loginViewModel,
                patientUpdateViewModel,
                viewManagerModel);
        updatePresenter.prepareFailView(ERROR);

        assertEquals(loginState, loginViewModel.getState());
        assertEquals(ERROR, patientUpdateViewModel.getState().getError());
        assertEquals(patientUpdateViewModel.getViewName(), viewManagerModel.getActiveView());
    }
}