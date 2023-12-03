
package app;

import interface_adapter.ViewManagerModel;
import interface_adapter.chat.refresh.ConversationRefreshViewModel;
import interface_adapter.choose_patient.ChoosePatientController;
import interface_adapter.choose_patient.ChoosePatientPresenter;
import interface_adapter.choose_patient.ChoosePatientViewModel;

import interface_adapter.login.LoginViewModel;
import interface_adapter.swap_views.chat.SwapToConversationController;
import interface_adapter.swap_views.chat.SwapToConversationPresenter;
import interface_adapter.load_patients.LoadPatientsController;
import interface_adapter.swap_views.login.SwapToLoginController;
import interface_adapter.swap_views.login.SwapToLoginPresenter;
import interface_adapter.swap_views.update.doctor.SwapToDoctorUpdateController;
import interface_adapter.swap_views.update.doctor.SwapToDoctorUpdatePresenter;
import interface_adapter.update.doctor.DoctorUpdateViewModel;
import use_case.choose_patient.*;
import use_case.load_patient.LoadPatientInputBoundary;
import use_case.load_patient.LoadPatientInteractor;

import use_case.swap_views.chat.SwapToConversationInputBoundary;
import use_case.swap_views.chat.SwapToConversationInteractor;
import use_case.swap_views.chat.SwapToConversationOutputBoundary;
import use_case.swap_views.login.SwapToLoginInputBoundary;
import use_case.swap_views.login.SwapToLoginInteractor;
import use_case.swap_views.login.SwapToLoginOutputBoundary;
import use_case.swap_views.update.doctor.SwapToDoctorUpdateInputBoundary;
import use_case.swap_views.update.doctor.SwapToDoctorUpdateInteractor;
import use_case.swap_views.update.doctor.SwapToDoctorUpdateOutputBoundary;
import view.ListOfPatientsView;

public class ChoosePatientUseCaseFactory {

    /* Prevent instantiation. */
    public ChoosePatientUseCaseFactory() {
    }

    public static ListOfPatientsView create(ViewManagerModel viewManagerModel,
                                            ConversationRefreshViewModel conversationViewModel,
                                            DoctorUpdateViewModel doctorUpdateViewModel,
                                            ChoosePatientViewModel choosePatientViewModel,
                                            LoginViewModel loginViewModel,
                                            ChoosePatientUserDataAccessInterface userDao) {
        ChoosePatientController updateController = createChoosePatientUseCase(viewManagerModel,
                conversationViewModel,
                choosePatientViewModel);

        SwapToLoginController swapController = createSwaptoLoginUseCase(viewManagerModel, loginViewModel);
        LoadPatientsController loadPatientsController = createLoadPatientsUseCase(userDao);
        SwapToDoctorUpdateController swapToDoctorUpdateController = createSwapToDoctorUpdateController(viewManagerModel, doctorUpdateViewModel);
        SwapToConversationController swapToConversationController = createSwapToConversationController(viewManagerModel, conversationViewModel);
        return new ListOfPatientsView(updateController,
                choosePatientViewModel,
                swapController,
                swapToConversationController,
                loadPatientsController,
                swapToDoctorUpdateController);
    }

    private static ChoosePatientController createChoosePatientUseCase(ViewManagerModel viewManagerModel,
                                                                      ConversationRefreshViewModel conversationViewModel,
                                                                      ChoosePatientViewModel choosePatientViewModel) {
        ChoosePatientOutputBoundary choosePatientPresenter = new ChoosePatientPresenter(
                viewManagerModel,
                conversationViewModel,
                choosePatientViewModel);

        ChoosePatientInputBoundary choosePatientInteractor = new ChoosePatientInteractor(choosePatientPresenter);
        return new ChoosePatientController(choosePatientInteractor);
    }

    private static SwapToLoginController createSwaptoLoginUseCase(ViewManagerModel viewManagerModel,
                                                                    LoginViewModel loginViewModel) {
        SwapToLoginOutputBoundary swapToLoginPresenter = new SwapToLoginPresenter(viewManagerModel, loginViewModel);
        SwapToLoginInputBoundary swapToLoginInteractor = new SwapToLoginInteractor(swapToLoginPresenter);
        return new SwapToLoginController(swapToLoginInteractor);
    }

    private static LoadPatientsController createLoadPatientsUseCase(ChoosePatientUserDataAccessInterface userDao) {
        LoadPatientInputBoundary loadPatientInteractor = new LoadPatientInteractor(userDao);
        return new LoadPatientsController(loadPatientInteractor);
    }

    private static SwapToDoctorUpdateController createSwapToDoctorUpdateController(ViewManagerModel viewManagerModel,
                                                                                   DoctorUpdateViewModel doctorUpdateViewModel) {
        SwapToDoctorUpdateOutputBoundary swapToDoctorUpdatePresenter = new SwapToDoctorUpdatePresenter(viewManagerModel, doctorUpdateViewModel);
        SwapToDoctorUpdateInputBoundary swapToDoctorUpdateInteractor = new SwapToDoctorUpdateInteractor(swapToDoctorUpdatePresenter);
        return new SwapToDoctorUpdateController(swapToDoctorUpdateInteractor);
    }

    public static SwapToConversationController createSwapToConversationController(ViewManagerModel viewManagerModel,
                                                                                  ConversationRefreshViewModel conversationRefreshViewModel) {
        SwapToConversationOutputBoundary swapToConversationPresenter = new SwapToConversationPresenter(viewManagerModel, conversationRefreshViewModel);
        SwapToConversationInputBoundary swapToConversationInteractor = new SwapToConversationInteractor(swapToConversationPresenter);
        return new SwapToConversationController(swapToConversationInteractor);
    }
}
