package interface_adapter.swap_views.login;

import interface_adapter.ViewManagerModel;
import interface_adapter.choosepatient.ChoosePatientViewModel;
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
    public void swapPatientUpdateToLoginTest() {
        ViewManagerModel viewManagerModel = new ViewManagerModel();
        PatientUpdateViewModel patientupdateViewModel = new PatientUpdateViewModel();
        LoginViewModel loginViewModel = new LoginViewModel();
        viewManagerModel.setActiveView(patientupdateViewModel.getViewName());

        SwapToLoginPresenter swapToLoginPresenter = new SwapToLoginPresenter(viewManagerModel, loginViewModel);
        swapToLoginPresenter.execute();

        assertEquals(viewManagerModel.getActiveView(), loginViewModel.getViewName());
    }

    @Test
    public void swapDoctorUpdateToLoginTest() {
        ViewManagerModel viewManagerModel = new ViewManagerModel();
        DoctorUpdateViewModel doctorupdateViewModel = new DoctorUpdateViewModel();
        LoginViewModel loginViewModel = new LoginViewModel();
        viewManagerModel.setActiveView(doctorupdateViewModel.getViewName());

        SwapToLoginPresenter swapToLoginPresenter = new SwapToLoginPresenter(viewManagerModel, loginViewModel);
        swapToLoginPresenter.execute();

        assertEquals(viewManagerModel.getActiveView(), loginViewModel.getViewName());
    }
}
