package app;


import data_access.DAOFacade;
import entity.people.PatientUserFactory;
import interface_adapter.ViewManagerModel;
import interface_adapter.chatbot.DialogflowViewModel;
import interface_adapter.login.LoginViewModel;
import interface_adapter.swap_views.chatbot.SwapToDialogflowController;
import interface_adapter.swap_views.chatbot.SwapToDialogflowPresenter;
import interface_adapter.update.patient.PatientUpdateController;
import interface_adapter.update.patient.PatientUpdatePresenter;
import interface_adapter.update.patient.PatientUpdateViewModel;
import use_case.swap_views.chatbot.SwapToDialogflowInputBoundary;
import use_case.swap_views.chatbot.SwapToDialogflowInteractor;
import use_case.swap_views.chatbot.SwapToDialogflowOutputBoundary;
import use_case.update.patient.PatientUpdateInputBoundary;
import use_case.update.patient.PatientUpdateInteractor;
import use_case.update.patient.PatientUpdateOutputBoundary;
import view.PatientUpdateView;

public class PatientUpdateUseCaseFactory {
    public static PatientUpdateView create(
            DAOFacade daoFacade,
            ViewManagerModel viewManagerModel,
            PatientUpdateViewModel patientUpdateViewModel,
            DialogflowViewModel dialogflowViewModel,
            LoginViewModel loginViewModel
    ) {
        SwapToDialogflowController swapToDialogflowController = createSwapToDialogflowUseCase(viewManagerModel, dialogflowViewModel);
        PatientUpdateController updateController = createPatientUpdateUseCase(daoFacade, patientUpdateViewModel, loginViewModel, viewManagerModel);
        return new PatientUpdateView(patientUpdateViewModel, swapToDialogflowController, updateController);
    }

    private static PatientUpdateController createPatientUpdateUseCase(DAOFacade daoFacade, PatientUpdateViewModel patientUpdateViewModel, LoginViewModel loginViewModel, ViewManagerModel viewManagerModel) {
        PatientUpdateOutputBoundary patientUpdatePresenter = new PatientUpdatePresenter(loginViewModel, patientUpdateViewModel, viewManagerModel);
        PatientUserFactory patientFactory = new PatientUserFactory();
        PatientUpdateInputBoundary patientInteractor = new PatientUpdateInteractor(daoFacade, patientUpdatePresenter, patientFactory);
        return new PatientUpdateController(patientInteractor);
    }

    private static SwapToDialogflowController createSwapToDialogflowUseCase(ViewManagerModel viewManagerModel, DialogflowViewModel dialogflowViewModel) {
        SwapToDialogflowOutputBoundary toDialogflowPresenter = new SwapToDialogflowPresenter(viewManagerModel, dialogflowViewModel);
        SwapToDialogflowInputBoundary toDialogflowInteractor = new SwapToDialogflowInteractor(toDialogflowPresenter);
        return new SwapToDialogflowController(toDialogflowInteractor);
    }
}
