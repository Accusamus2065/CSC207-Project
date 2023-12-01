package interface_adapter.swap_views.list_of_patients;

import interface_adapter.ViewManagerModel;
import interface_adapter.choose_patient.ChoosePatientViewModel;
import interface_adapter.update.doctor.DoctorUpdateViewModel;
import org.junit.Test;

import static org.junit.Assert.*;

public class SwapToPatientListPresenterTest {
    @Test
    public void swapModifyPatientToChoosePatientTest() {
        ViewManagerModel viewManagerModel = new ViewManagerModel();
        DoctorUpdateViewModel doctorUpdateViewModel = new DoctorUpdateViewModel();
        ChoosePatientViewModel choosePatientViewModel = new ChoosePatientViewModel();
        viewManagerModel.setActiveView(doctorUpdateViewModel.getViewName());

        SwapToPatientListPresenter toPatientListPresenter = new SwapToPatientListPresenter(viewManagerModel, choosePatientViewModel);
        toPatientListPresenter.swapViews();

        assertEquals(choosePatientViewModel.getViewName(), viewManagerModel.getActiveView());
    }
}