package interface_adapter.swap_views.login;

import interface_adapter.ViewManagerModel;
import interface_adapter.chatbot.DialogflowViewModel;
import interface_adapter.choose_patient.ChoosePatientViewModel;
import interface_adapter.login.LoginViewModel;
import interface_adapter.update.doctor.DoctorUpdateViewModel;
import interface_adapter.update.patient.PatientUpdateViewModel;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class SwapToLoginPresenterTest {
    @Test
    public void swapChoosePatientToLoginTest() {
        ViewManagerModel viewManagerModel = new ViewManagerModel();
        ChoosePatientViewModel choosePatientViewModel = new ChoosePatientViewModel();
        LoginViewModel loginViewModel = new LoginViewModel();
        viewManagerModel.setActiveView(choosePatientViewModel.getViewName());

        SwapToLoginPresenter swapToLoginPresenter = new SwapToLoginPresenter(viewManagerModel, loginViewModel);
        swapToLoginPresenter.execute();

        assertEquals(viewManagerModel.getActiveView(), loginViewModel.getViewName());
    }

    @Test
    public void swapDialogflowToLoginTest() {
        ViewManagerModel viewManagerModel = new ViewManagerModel();
        DialogflowViewModel dialogflowViewModel = new DialogflowViewModel();
        LoginViewModel loginViewModel = new LoginViewModel();
        viewManagerModel.setActiveView(dialogflowViewModel.getViewName());

        SwapToLoginPresenter swapToLoginPresenter = new SwapToLoginPresenter(viewManagerModel, loginViewModel);
        swapToLoginPresenter.execute();

        assertEquals(viewManagerModel.getActiveView(), loginViewModel.getViewName());
    }
}
