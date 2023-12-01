package app;

import data_access.DialogflowDAOImpl;
import interface_adapter.ViewManagerModel;
import interface_adapter.chat.ConversationController;
import interface_adapter.chat.ConversationPresenter;
import interface_adapter.chatbot.DialogflowController;
import interface_adapter.chatbot.DialogflowPresenter;
import interface_adapter.chatbot.DialogflowViewModel;
import use_case.chat.ConversationInputBoundary;
import use_case.chat.ConversationInteractor;
import use_case.chat.ConversationOutputBoundary;
import use_case.chatbot.DialogflowInputBoundary;
import use_case.chatbot.DialogflowInteractor;
import use_case.chatbot.DialogflowOutputBoundary;
import view.DialogflowView;

import javax.swing.*;
import java.io.IOException;

public class DialogflowUseCaseFactory {
    /** Prevent instantiation. */
    private DialogflowUseCaseFactory() {}


    public static DialogflowView create(
            ViewManagerModel viewManagerModel,
            DialogflowViewModel viewModel,
            DialogflowDAOImpl dao, String username) {

        try {
            DialogflowController controller = createDialogflowConroller(viewManagerModel, viewModel, dao);
            return new DialogflowView(viewModel, controller, username);
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Could not open user data file.");
        }

        return null;
    }

    public static DialogflowController createDialogflowConroller(ViewManagerModel viewManagerModel, DialogflowViewModel dialogflowViewModel, DialogflowDAOImpl dao) throws IOException {
        DialogflowOutputBoundary outputBoundary = new DialogflowPresenter(viewManagerModel, dialogflowViewModel);
        DialogflowInputBoundary inputInteractor = new DialogflowInteractor(
                dao, outputBoundary);

        return new DialogflowController(inputInteractor);
    }
}
