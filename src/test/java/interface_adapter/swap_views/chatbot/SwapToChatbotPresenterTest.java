package interface_adapter.swap_views.chatbot;

import interface_adapter.ViewManagerModel;
import interface_adapter.chatbot.DialogflowViewModel;
import interface_adapter.update.patient.PatientUpdateViewModel;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class SwapToChatbotPresenterTest {
    @Test
    public void swapPatientUpdateToDialogflowTest() {
        ViewManagerModel viewManagerModel = new ViewManagerModel();
        DialogflowViewModel dialogflowViewModel = new DialogflowViewModel();
        PatientUpdateViewModel patientUpdateViewModel = new PatientUpdateViewModel();
        viewManagerModel.setActiveView(patientUpdateViewModel.getViewName());

        SwapToChatbotPresenter swap = new SwapToChatbotPresenter(viewManagerModel, dialogflowViewModel);
        swap.swapViews();

        assertEquals(dialogflowViewModel.getViewName(), viewManagerModel.getActiveView());
    }
}
