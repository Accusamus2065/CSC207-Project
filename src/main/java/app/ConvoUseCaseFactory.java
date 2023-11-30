package app;

import data_access.ConvoDAOImpl;
import interface_adapter.ViewManagerModel;
import interface_adapter.chat.ConversationController;
import interface_adapter.chat.ConversationPresenter;
import interface_adapter.chat.ConversationViewModel;
import interface_adapter.login.LoginController;
import interface_adapter.login.LoginViewModel;
import use_case.chat.ConversationInputBoundary;
import use_case.chat.ConversationInteractor;
import use_case.chat.ConversationOutputBoundary;
import use_case.login.LoginUserDataAccessInterface;
import view.ConversationView;
import view.LoginView;

import javax.swing.*;
import java.io.IOException;

public class ConvoUseCaseFactory {

    /** Prevent instantiation. */
    private ConvoUseCaseFactory() {}


    public static ConversationView create(
            ViewManagerModel viewManagerModel,
            ConversationViewModel viewModel,
            ConvoDAOImpl dao, String selfUsername, String otherUsername) {

        try {
            ConversationController controller = createConversationConroller(viewManagerModel, viewModel, dao);
            return new ConversationView(viewModel, controller, selfUsername, otherUsername);
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Could not open user data file.");
        }

        return null;
    }

    public static ConversationController createConversationConroller(ViewManagerModel viewManagerModel, ConversationViewModel clearViewModel, ConvoDAOImpl dao) throws IOException {
        ConversationOutputBoundary outputBoundary = new ConversationPresenter(viewManagerModel, clearViewModel);
        ConversationInputBoundary inputInteractor = new ConversationInteractor(
                dao, outputBoundary);

        return new ConversationController(inputInteractor);
    }
}