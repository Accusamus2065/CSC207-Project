package app;

import data_access.DAOFacade;
import interface_adapter.ViewManagerModel;
import interface_adapter.chatbot.DialogflowController;
import interface_adapter.chatbot.DialogflowPresenter;
import interface_adapter.chatbot.DialogflowViewModel;
import interface_adapter.login.LoginViewModel;
import interface_adapter.swap_views.login.SwapToLoginController;
import interface_adapter.swap_views.login.SwapToLoginPresenter;
import use_case.chatbot.DialogflowInputBoundary;
import use_case.chatbot.DialogflowInteractor;
import use_case.chatbot.DialogflowOutputBoundary;
import use_case.swap_views.login.SwapToLoginInteractor;
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
            DAOFacade userDataAccessObject
    ) {
        DialogflowController controller = createDialogflowController(viewManagerModel, viewModel, userDataAccessObject);
        SwapToLoginController loginController = createLoginUseCase(viewManagerModel, loginViewModel);
        return new DialogflowView(viewModel, loginController, controller);

    }

    private static SwapToLoginController createLoginUseCase(ViewManagerModel viewManagerModel, LoginViewModel loginViewModel) {
        SwapToLoginPresenter loginPresenter = new SwapToLoginPresenter(viewManagerModel, loginViewModel);
        SwapToLoginInteractor loginInteractor = new SwapToLoginInteractor(loginPresenter);
        return new SwapToLoginController(loginInteractor);


    }

    public static DialogflowController createDialogflowController(ViewManagerModel viewManagerModel,
                                                                  DialogflowViewModel dialogflowViewModel,
                                                                  DAOFacade userDataAccessObject) {
        DialogflowOutputBoundary outputBoundary = new DialogflowPresenter(viewManagerModel, dialogflowViewModel);
        DialogflowInputBoundary inputInteractor = new DialogflowInteractor(userDataAccessObject, outputBoundary);

        return new DialogflowController(inputInteractor);
    }
}
