package interface_adapter.update.doctor;

import interface_adapter.ViewManagerModel;
import interface_adapter.login.LoginState;
import interface_adapter.login.LoginViewModel;
import org.junit.Test;
import use_case.update.doctor.DoctorUpdateOutputData;

import static org.junit.Assert.*;

public class DoctorUpdatePresenterTest {
    private static final String USERNAME = "patientUpdatePresenterTestUsername";
    private static final String ERROR = "patientUpdatePresenterTestError";
    @Test
    public void successUpdateTest() {
        ViewManagerModel viewManagerModel = new ViewManagerModel();
        LoginViewModel loginViewModel = new LoginViewModel();
        DoctorUpdateViewModel doctorUpdateViewModel = new DoctorUpdateViewModel();

        DoctorUpdatePresenter updatePresenter = new DoctorUpdatePresenter(doctorUpdateViewModel, loginViewModel, viewManagerModel);
        DoctorUpdateOutputData outputData = new DoctorUpdateOutputData(USERNAME, false);
        updatePresenter.prepareSuccessView(outputData);

        assertEquals(loginViewModel.getViewName(), viewManagerModel.getActiveView());
        assertEquals(USERNAME, loginViewModel.getState().getUsername());
    }

    @Test
    public void failUpdateTest() {
        ViewManagerModel viewManagerModel = new ViewManagerModel();
        LoginViewModel loginViewModel = new LoginViewModel();
        LoginState loginState = loginViewModel.getState();
        DoctorUpdateViewModel doctorUpdateViewModel = new DoctorUpdateViewModel();
        viewManagerModel.setActiveView(doctorUpdateViewModel.getViewName());

        DoctorUpdatePresenter updatePresenter = new DoctorUpdatePresenter(doctorUpdateViewModel, loginViewModel, viewManagerModel);
        updatePresenter.prepareFailView(ERROR);

        assertEquals(loginState, loginViewModel.getState());
        assertEquals(ERROR, doctorUpdateViewModel.getState().getError());
        assertEquals(doctorUpdateViewModel.getViewName(), viewManagerModel.getActiveView());
    }
}