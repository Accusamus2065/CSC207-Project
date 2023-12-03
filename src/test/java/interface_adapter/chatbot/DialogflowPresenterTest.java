
package interface_adapter.chatbot;

import interface_adapter.ViewManagerModel;
import org.junit.Test;
import use_case.chatbot.DialogflowOutputData;

import java.util.ArrayList;

import static org.junit.Assert.*;

public class DialogflowPresenterTest {
    @Test
    public void successViewTest() {
        ViewManagerModel viewManagerModel = new ViewManagerModel();
        DialogflowViewModel dialogflowViewModel = new DialogflowViewModel();
        viewManagerModel.setActiveView(dialogflowViewModel.getViewName());

        DialogflowOutputData outputData = new DialogflowOutputData("DialogflowPresenterTestResponse", "a", new ArrayList<>());


        DialogflowPresenter dialogflowPresenter = new DialogflowPresenter(viewManagerModel, dialogflowViewModel);
        dialogflowPresenter.prepareSuccessView(outputData);

        assertEquals(dialogflowViewModel.getViewName(), viewManagerModel.getActiveView());
        assertEquals(outputData.getResponse(), dialogflowViewModel.getState().getResponse());
    }
}
