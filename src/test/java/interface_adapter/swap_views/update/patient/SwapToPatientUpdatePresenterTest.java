package interface_adapter.swap_views.update.patient;

import interface_adapter.ViewManagerModel;
import interface_adapter.chatbot.DialogflowViewModel;
import interface_adapter.update.patient.PatientUpdateViewModel;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class SwapToPatientUpdatePresenterTest {
    @Test
    public void swapDialogflowToPatientUpdateTest() {
        ViewManagerModel viewManagerModel = new ViewManagerModel();
        DialogflowViewModel dialogflowViewModel = new DialogflowViewModel();
        PatientUpdateViewModel patientUpdateViewModel = new PatientUpdateViewModel();
        viewManagerModel.setActiveView(dialogflowViewModel.getViewName());

        SwapToPatientUpdatePresenter swap = new SwapToPatientUpdatePresenter(viewManagerModel, patientUpdateViewModel);
        swap.execute("SamplePatient");

        assertEquals(viewManagerModel.getActiveView(), patientUpdateViewModel.getViewName());
        assertEquals(patientUpdateViewModel.getState().getUsername(), "SamplePatient");
    }
}
