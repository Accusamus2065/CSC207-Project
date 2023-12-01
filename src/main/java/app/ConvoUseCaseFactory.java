package app;

import data_access.DAOFacade;
import interface_adapter.ViewManagerModel;
import interface_adapter.chat.ConversationController;
import interface_adapter.chat.ConversationPresenter;
import interface_adapter.chat.ConversationViewModel;
import use_case.chat.ConversationInputBoundary;
import use_case.chat.ConversationInteractor;
import use_case.chat.ConversationOutputBoundary;
import view.ConversationView;

import javax.swing.*;
import java.io.IOException;

public class ConvoUseCaseFactory {

    /**
     * Prevent instantiation.
     */
    private ConvoUseCaseFactory() {
    }

    public static ConversationView create(
            ViewManagerModel viewManagerModel,
            ConversationViewModel viewModel,
            DAOFacade dao,
            String selfUsername,
            String otherUsername) {

        try {
            ConversationController controller = createConversationController(viewManagerModel, viewModel, dao);
            return new ConversationView(viewModel, controller, selfUsername, otherUsername);
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Could not open user data file.");
        }
        return null;
    }

    public static ConversationController createConversationController(
            ViewManagerModel viewManagerModel,
            ConversationViewModel clearViewModel,
            DAOFacade dao
    ) throws IOException {
        ConversationOutputBoundary outputBoundary = new ConversationPresenter(viewManagerModel, clearViewModel);
        ConversationInputBoundary inputInteractor = new ConversationInteractor(
                dao, outputBoundary);

        return new ConversationController(inputInteractor);
    }
}
