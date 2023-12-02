package app;

import data_access.DAOFacade;
import interface_adapter.ViewManagerModel;
import interface_adapter.chat.refresh.ConversationRefreshController;
import interface_adapter.chat.refresh.ConversationRefreshPresenter;
import interface_adapter.chat.refresh.ConversationRefreshViewModel;
import interface_adapter.chat.save.ConversationSaveController;
import interface_adapter.login.LoginController;
import interface_adapter.login.LoginViewModel;
import use_case.chat.refresh.ConversationRefreshInputBoundary;
import use_case.chat.refresh.ConversationRefreshInteractor;
import use_case.chat.refresh.ConversationRefreshOutputBoundary;
import use_case.chat.save.ConversationSaveInputBoundary;
import use_case.chat.save.ConversationSaveInteractor;
import use_case.login.LoginUserDataAccessInterface;
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
            ConversationRefreshViewModel refreshViewModel,
            DAOFacade dao, // TODO: why concrete class and not an interface?
            String selfUsername,
            String otherUsername) {

        try {
            ConversationRefreshController refreshController = createConversationRefreshConroller(viewManagerModel, refreshViewModel, dao);
            ConversationSaveController saveController = createConversationSaveController(dao);
            return new ConversationView(refreshViewModel, refreshController, saveController, selfUsername, otherUsername);
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Could not open user data file.");
        }
        return null;
    }

    private static ConversationRefreshController createConversationRefreshConroller (
            ViewManagerModel viewManagerModel,
            ConversationRefreshViewModel refreshViewModel,
            ConvoDAOImpl dao
    ) throws IOException {
        ConversationRefreshOutputBoundary refreshOutputBoundary = new ConversationRefreshPresenter(viewManagerModel,
                refreshViewModel);
        ConversationRefreshInputBoundary refreshInteractor = new ConversationRefreshInteractor(
                dao, refreshOutputBoundary);

        return new ConversationRefreshController(refreshInteractor);
    }

    private static ConversationSaveController createConversationSaveController (
            ConvoDAOImpl dao
    ) throws IOException {
        ConversationSaveInputBoundary saveInteractor = new ConversationSaveInteractor(dao);
        return new ConversationSaveController(saveInteractor);
    }
}
