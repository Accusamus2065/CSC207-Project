package interface_adapter.choosepatient;

import interface_adapter.ViewManagerModel;
import interface_adapter.chat.ConversationViewModel;
import interface_adapter.update.doctor.DoctorUpdateViewModel;
import org.junit.Test;
import use_case.choosepatient.ChoosePatientOutputData;

public class ChoosePatientPresenterTest {

    private static final String USERNAME = "SampleUser";
    private static final String ERROR = "SAMPLE ERROR !!!!!";

    @Test
    public void successChoosePatientTest() {
        // Arrange
        ViewManagerModel viewManagerModel = new ViewManagerModel();
        ConversationViewModel conversationViewModel = new ConversationViewModel();
        ChoosePatientViewModel choosePatientViewModel = new ChoosePatientViewModel();
        DoctorUpdateViewModel doctorUpdateViewModel = new DoctorUpdateViewModel();

        ChoosePatientPresenter choosePatientPresenter = new ChoosePatientPresenter(
                viewManagerModel, conversationViewModel, null, doctorUpdateViewModel, choosePatientViewModel);

        // Act
        ChoosePatientOutputData outputData = new ChoosePatientOutputData(USERNAME, "TestPatient", false);
        choosePatientPresenter.prepareSuccessView(outputData);

        // Assert
        // TODO: UNCOMMENT TESTS WHEN CONVERSATION IS READY
//        assertEquals(conversationViewModel.getViewName(), viewManagerModel.getActiveView());
//        assertEquals(USERNAME, conversationViewModel.getState().getUsername());
//        assertNull(choosePatientViewModel.getState().getError());
    }

    @Test
    public void failChoosePatientTest() {
        // Arrange
        ViewManagerModel viewManagerModel = new ViewManagerModel();
        ConversationViewModel conversationViewModel = new ConversationViewModel();
        ChoosePatientViewModel choosePatientViewModel = new ChoosePatientViewModel();
        DoctorUpdateViewModel doctorUpdateViewModel = new DoctorUpdateViewModel();

        ChoosePatientPresenter choosePatientPresenter = new ChoosePatientPresenter(
                viewManagerModel, conversationViewModel, null, doctorUpdateViewModel, choosePatientViewModel);

        // Act
        choosePatientPresenter.prepareFailView(ERROR);

        // TODO: UNCOMMENT BELOW TESTS WHEN IT IS READY:
        // Assert
//        assertEquals(choosePatientViewModel.getViewName(), viewManagerModel.getActiveView());
//        assertEquals(ERROR, choosePatientViewModel.getState().getError());
    }
}
