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
import use_case.chatbot.DialogflowUserDataAccessInterface;
import view.DialogflowView;

import javax.swing.*;
import java.io.IOException;

public class DialogflowUseCaseFactory {
    /**
     * Prevent instantiation.
     */
    private DialogflowUseCaseFactory() {
    }


    public static DialogflowView create(
            ViewManagerModel viewManagerModel,
            DialogflowViewModel viewModel,
            DialogflowUserDataAccessInterface userDataAccessObject,
            String username) {
        DialogflowController controller = createDialogflowController(viewManagerModel, viewModel, userDataAccessObject);
        return new DialogflowView(viewModel, controller, username);
    }

    public static DialogflowController createDialogflowController(ViewManagerModel viewManagerModel,
                                                                  DialogflowViewModel dialogflowViewModel,
                                                                  DialogflowUserDataAccessInterface userDataAccessObject) {
        DialogflowOutputBoundary outputBoundary = new DialogflowPresenter(viewManagerModel, dialogflowViewModel);
        DialogflowInputBoundary inputInteractor = new DialogflowInteractor(userDataAccessObject, outputBoundary);

        return new DialogflowController(inputInteractor);
    }
}
