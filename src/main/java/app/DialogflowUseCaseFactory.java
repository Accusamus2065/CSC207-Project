package app;

import data_access.DAOFacade;
import interface_adapter.ViewManagerModel;
import interface_adapter.chatbot.DialogflowController;
import interface_adapter.chatbot.DialogflowPresenter;
import interface_adapter.chatbot.DialogflowViewModel;
import use_case.chatbot.DialogflowInputBoundary;
import use_case.chatbot.DialogflowInteractor;
import use_case.chatbot.DialogflowOutputBoundary;
import view.DialogflowView;


public class DialogflowUseCaseFactory {
    /**
     * Prevent instantiation.
     */
    private DialogflowUseCaseFactory() {
    }

    public static DialogflowView create(
            ViewManagerModel viewManagerModel,
            DialogflowViewModel viewModel,
            DAOFacade userDataAccessObject) {
        DialogflowController controller = createDialogflowController(viewManagerModel, viewModel, userDataAccessObject);
        return new DialogflowView(viewModel, controller);
    }

    public static DialogflowController createDialogflowController(ViewManagerModel viewManagerModel,
                                                                  DialogflowViewModel dialogflowViewModel,
                                                                  DAOFacade userDataAccessObject) {
        DialogflowOutputBoundary outputBoundary = new DialogflowPresenter(viewManagerModel, dialogflowViewModel);
        DialogflowInputBoundary inputInteractor = new DialogflowInteractor(userDataAccessObject, outputBoundary);

        return new DialogflowController(inputInteractor);
    }
}
