package interface_adapter.swap_views.update.doctor;

import interface_adapter.ViewManagerModel;
import interface_adapter.choosepatient.ChoosePatientViewModel;
import interface_adapter.login.LoginViewModel;
import interface_adapter.swap_views.login.SwapToLoginPresenter;
import interface_adapter.update.doctor.DoctorUpdateViewModel;
import org.junit.Test;
import use_case.swap_views.update.doctor.SwapToDoctorUpdateOutputBoundary;

import static org.junit.Assert.assertEquals;

public class SwapToDoctorUpdatePresenterTest {

    @Test
    public void swapChoosePatientToDoctorUpdateTest() {
        ViewManagerModel viewManagerModel = new ViewManagerModel();
        ChoosePatientViewModel choosePatientViewModel = new ChoosePatientViewModel();
        DoctorUpdateViewModel doctorUpdateViewModel = new DoctorUpdateViewModel();
        viewManagerModel.setActiveView(choosePatientViewModel.getViewName());

        SwapToDoctorUpdatePresenter swap = new SwapToDoctorUpdatePresenter(viewManagerModel, doctorUpdateViewModel);
        swap.execute("SampleDoctor");

        assertEquals(viewManagerModel.getActiveView(), doctorUpdateViewModel.getViewName());
        assertEquals(doctorUpdateViewModel.getState().getUsername(), "SampleDoctor");
    }

}
