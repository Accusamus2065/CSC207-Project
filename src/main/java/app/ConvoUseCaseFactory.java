package app;

import interface_adapter.ViewManagerModel;
import interface_adapter.chat.refresh.ConversationRefreshController;
import interface_adapter.chat.refresh.ConversationRefreshPresenter;
import interface_adapter.chat.refresh.ConversationRefreshViewModel;
import interface_adapter.chat.save.ConversationSaveController;
import interface_adapter.chatbot.DialogflowViewModel;
import interface_adapter.choose_patient.ChoosePatientViewModel;
import interface_adapter.login.LoginViewModel;
import interface_adapter.swap_views.chatbot.SwapToDialogflowController;
import interface_adapter.swap_views.chatbot.SwapToDialogflowPresenter;
import interface_adapter.swap_views.list_of_patients.SwapToPatientListController;
import interface_adapter.swap_views.list_of_patients.SwapToPatientListPresenter;
import interface_adapter.swap_views.login.SwapToLoginController;
import interface_adapter.swap_views.login.SwapToLoginPresenter;
import use_case.chat.ConversationUserDataAccessInterface;
import use_case.chat.refresh.ConversationRefreshInputBoundary;
import use_case.chat.refresh.ConversationRefreshInteractor;
import use_case.chat.refresh.ConversationRefreshOutputBoundary;
import use_case.chat.save.ConversationSaveInputBoundary;
import use_case.chat.save.ConversationSaveInteractor;
import use_case.swap_views.chatbot.SwapToDialogflowInteractor;
import use_case.swap_views.list_of_patients.SwapToPatientListInteractor;
import use_case.swap_views.login.SwapToLoginInteractor;
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
            LoginViewModel loginViewModel,
            ConversationRefreshViewModel refreshViewModel,
            DialogflowViewModel dialogflowViewModel,
            ChoosePatientViewModel choosePatientViewModel,
            ConversationUserDataAccessInterface dao) {
        try {
            ConversationRefreshController refreshController = createConversationRefreshController(viewManagerModel, refreshViewModel, dao);
            ConversationSaveController saveController = createConversationSaveController(dao);
            SwapToLoginController swapController = createLoginController(viewManagerModel, loginViewModel);
            SwapToDialogflowController swapToDialogflowController = createDialogFlowController(viewManagerModel, dialogflowViewModel);
            SwapToPatientListController swapToPatientListController = createPatientListController(viewManagerModel, choosePatientViewModel);
            return new ConversationView(refreshViewModel, swapController, refreshController, saveController, swapToDialogflowController, swapToPatientListController);
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Could not open user data file.");
        }
        return null;
    }

    private static SwapToPatientListController createPatientListController(ViewManagerModel viewManagerModel, ChoosePatientViewModel choosePatientViewModel) {
        SwapToPatientListPresenter swapToPatientListPresenter = new SwapToPatientListPresenter(viewManagerModel, choosePatientViewModel);
        SwapToPatientListInteractor swapToPatientListInteractor = new SwapToPatientListInteractor(swapToPatientListPresenter);
        return new SwapToPatientListController(swapToPatientListInteractor);
    }

    private static SwapToDialogflowController createDialogFlowController(ViewManagerModel viewManagerModel, DialogflowViewModel dialogflowViewModel) {
        SwapToDialogflowPresenter swapToDialogflowPresenter = new SwapToDialogflowPresenter(viewManagerModel, dialogflowViewModel);
        SwapToDialogflowInteractor swapToDialogflowInteractor = new SwapToDialogflowInteractor(swapToDialogflowPresenter);
        return new SwapToDialogflowController(swapToDialogflowInteractor);
    }



    private static ConversationRefreshController createConversationRefreshController(
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

    private static SwapToLoginController createLoginController(ViewManagerModel viewManagerModel, LoginViewModel loginViewModel) {
        SwapToLoginPresenter loginPresenter = new SwapToLoginPresenter(viewManagerModel, loginViewModel);
        SwapToLoginInteractor loginInteractor = new SwapToLoginInteractor(loginPresenter);
        return new SwapToLoginController(loginInteractor);
    }

    private static ConversationSaveController createConversationSaveController(
            ConversationUserDataAccessInterface dao
    ) throws IOException {
        ConversationSaveInputBoundary saveInteractor = new ConversationSaveInteractor(dao);
        return new ConversationSaveController(saveInteractor);
    }
}
