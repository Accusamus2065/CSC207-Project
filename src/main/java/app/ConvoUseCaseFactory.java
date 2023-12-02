package app;

import interface_adapter.ViewManagerModel;
import interface_adapter.chat.refresh.ConversationRefreshController;
import interface_adapter.chat.refresh.ConversationRefreshPresenter;
import interface_adapter.chat.refresh.ConversationRefreshViewModel;
import interface_adapter.chat.save.ConversationSaveController;
import use_case.chat.ConversationUserDataAccessInterface;
import use_case.chat.refresh.ConversationRefreshInputBoundary;
import use_case.chat.refresh.ConversationRefreshInteractor;
import use_case.chat.refresh.ConversationRefreshOutputBoundary;
import use_case.chat.save.ConversationSaveInputBoundary;
import use_case.chat.save.ConversationSaveInteractor;
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
            ConversationUserDataAccessInterface dao,
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
            ConversationUserDataAccessInterface dao
    ) throws IOException {
        ConversationRefreshOutputBoundary refreshOutputBoundary = new ConversationRefreshPresenter(viewManagerModel,
                refreshViewModel);
        ConversationRefreshInputBoundary refreshInteractor = new ConversationRefreshInteractor(
                dao, refreshOutputBoundary);

        return new ConversationRefreshController(refreshInteractor);
    }

    private static ConversationSaveController createConversationSaveController (
            ConversationUserDataAccessInterface dao
    ) throws IOException {
        ConversationSaveInputBoundary saveInteractor = new ConversationSaveInteractor(dao);
        return new ConversationSaveController(saveInteractor);
    }
}
