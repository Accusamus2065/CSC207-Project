package app;

import interface_adapter.ViewManagerModel;
import interface_adapter.chat.refresh.ConversationRefreshViewModel;
import interface_adapter.chatbot.DialogflowController;
import interface_adapter.chatbot.DialogflowPresenter;
import interface_adapter.chatbot.DialogflowViewModel;
import interface_adapter.login.LoginViewModel;
import interface_adapter.swap_views.chat.SwapToConversationController;
import interface_adapter.swap_views.chat.SwapToConversationPresenter;
import interface_adapter.swap_views.login.SwapToLoginController;
import interface_adapter.swap_views.login.SwapToLoginPresenter;
import interface_adapter.swap_views.update.patient.SwapToPatientUpdateController;
import interface_adapter.swap_views.update.patient.SwapToPatientUpdatePresenter;
import interface_adapter.update.patient.PatientUpdateViewModel;
import use_case.chatbot.DialogflowInputBoundary;
import use_case.chatbot.DialogflowInteractor;
import use_case.chatbot.DialogflowOutputBoundary;
import use_case.chatbot.DialogflowUserDataAccessInterface;
import use_case.swap_views.chat.SwapToConversationInputBoundary;
import use_case.swap_views.chat.SwapToConversationInteractor;
import use_case.swap_views.chat.SwapToConversationOutputBoundary;
import use_case.swap_views.login.SwapToLoginInteractor;
import use_case.swap_views.update.patient.SwapToPatientUpdateInteractor;
import view.DialogflowView;


public class DialogflowUseCaseFactory {
    /**
     * Prevent instantiation.
     */
    private DialogflowUseCaseFactory() {
    }

    public static DialogflowView create(
            ViewManagerModel viewManagerModel,
            LoginViewModel loginViewModel,
            DialogflowViewModel viewModel,
            ConversationRefreshViewModel conversationRefreshViewModel,
            PatientUpdateViewModel patientUpdateViewModel,
            DialogflowUserDataAccessInterface userDataAccessObject
    ) {
        DialogflowController controller = createDialogflowController(viewManagerModel, viewModel, userDataAccessObject);
        SwapToLoginController loginController = createLoginController(viewManagerModel, loginViewModel);
        SwapToConversationController swapController = createSwapToConversationController(viewManagerModel, conversationRefreshViewModel);
        SwapToPatientUpdateController updateController = createSwapToPatientUpdateController(viewManagerModel, patientUpdateViewModel);
        return new DialogflowView(viewModel, loginController, controller, swapController, updateController);

    }

    private static SwapToLoginController createLoginController(ViewManagerModel viewManagerModel, LoginViewModel loginViewModel) {
        SwapToLoginPresenter loginPresenter = new SwapToLoginPresenter(viewManagerModel, loginViewModel);
        SwapToLoginInteractor loginInteractor = new SwapToLoginInteractor(loginPresenter);
        return new SwapToLoginController(loginInteractor);
    }

    public static DialogflowController createDialogflowController(ViewManagerModel viewManagerModel,
                                                                  DialogflowViewModel dialogflowViewModel,
                                                                  DialogflowUserDataAccessInterface userDataAccessObject) {
        DialogflowOutputBoundary outputBoundary = new DialogflowPresenter(viewManagerModel, dialogflowViewModel);
        DialogflowInputBoundary inputInteractor = new DialogflowInteractor(userDataAccessObject, outputBoundary);

        return new DialogflowController(inputInteractor);
    }

    public static SwapToPatientUpdateController createSwapToPatientUpdateController(ViewManagerModel viewManagerModel,
                                                                                    PatientUpdateViewModel patientUpdateViewModel) {
        SwapToPatientUpdatePresenter patientUpdatePresenter = new SwapToPatientUpdatePresenter(viewManagerModel, patientUpdateViewModel);
        SwapToPatientUpdateInteractor patientUpdateInteractor = new SwapToPatientUpdateInteractor(patientUpdatePresenter);
        return new SwapToPatientUpdateController(patientUpdateInteractor);
    }

    public static SwapToConversationController createSwapToConversationController(ViewManagerModel viewManagerModel,
                                                                                  ConversationRefreshViewModel conversationRefreshViewModel) {
        SwapToConversationOutputBoundary swapToConversationPresenter = new SwapToConversationPresenter(viewManagerModel, conversationRefreshViewModel);
        SwapToConversationInputBoundary swapToConversationInteractor = new SwapToConversationInteractor(swapToConversationPresenter);
        return new SwapToConversationController(swapToConversationInteractor);
    }
}
