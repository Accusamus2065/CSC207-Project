package app;

import data_access.DAOFacade;

import interface_adapter.ViewManagerModel;
import interface_adapter.chat.refresh.ConversationRefreshViewModel;
import interface_adapter.choose_patient.ChoosePatientController;
import interface_adapter.choose_patient.ChoosePatientPresenter;
import interface_adapter.choose_patient.ChoosePatientViewModel;

import interface_adapter.swap_views.load_patients.LoadPatientsController;
import interface_adapter.swap_views.update.doctor.SwapToDoctorUpdateController;
import interface_adapter.swap_views.update.doctor.SwapToDoctorUpdatePresenter;
import interface_adapter.swap_views.welcome.SwapToWelcomeController;
import interface_adapter.swap_views.welcome.SwapToWelcomePresenter;
import interface_adapter.update.doctor.DoctorUpdateViewModel;
import interface_adapter.welcome.WelcomeViewModel;
import use_case.choose_patient.*;
import use_case.load_patient.LoadPatientInputBoundary;
import use_case.load_patient.LoadPatientInteractor;

import use_case.swap_views.update.doctor.SwapToDoctorUpdateInputBoundary;
import use_case.swap_views.update.doctor.SwapToDoctorUpdateInteractor;
import use_case.swap_views.update.doctor.SwapToDoctorUpdateOutputBoundary;
import use_case.swap_views.welcome.SwapToWelcomeInputBoundary;
import use_case.swap_views.welcome.SwapToWelcomeInteractor;
import use_case.swap_views.welcome.SwapToWelcomeOutputBoundary;
import view.ListOfPatientsView;

public class ChoosePatientUseCaseFactory {

    /* Prevent instantiation. */
    public ChoosePatientUseCaseFactory() { }

    public static ListOfPatientsView create(ViewManagerModel viewManagerModel,
                                            ConversationRefreshViewModel conversationViewModel,
                                            WelcomeViewModel welcomeViewModel,
                                            DoctorUpdateViewModel doctorUpdateViewModel,
                                            ChoosePatientViewModel choosePatientViewModel,
                                            DAOFacade userDao) {
        ChoosePatientController updateController = createChoosePatientUseCase(  viewManagerModel,
                                                                                conversationViewModel,
                                                                                welcomeViewModel,
                                                                                doctorUpdateViewModel,
                                                                                choosePatientViewModel);

        SwapToWelcomeController swapController = createSwapToWelcomeUseCase(viewManagerModel, welcomeViewModel);
        LoadPatientsController loadPatientsController = createLoadPatientsUseCase(userDao);
        SwapToDoctorUpdateController swapToDoctorUpdateController = createSwapToDoctorUpdateController(viewManagerModel,
                doctorUpdateViewModel);
        return new ListOfPatientsView(updateController,
                                        choosePatientViewModel,
                                        swapController,
                                        loadPatientsController,
                                        swapToDoctorUpdateController);
    }

    private static ChoosePatientController createChoosePatientUseCase(ViewManagerModel viewManagerModel,
                                                                      ConversationRefreshViewModel conversationViewModel,
                                                                      WelcomeViewModel welcomeViewModel,
                                                                      DoctorUpdateViewModel doctorUpdateViewModel,
                                                                      ChoosePatientViewModel choosePatientViewModel) {
        ChoosePatientOutputBoundary choosepatientPresenter = new ChoosePatientPresenter(
                                                                                        viewManagerModel,
                                                                                        conversationViewModel,
                                                                                        welcomeViewModel,
                                                                                        doctorUpdateViewModel,
                                                                                        choosePatientViewModel);

        ChoosePatientInputBoundary choosepatientInteractor = new ChoosePatientInteractor(choosepatientPresenter);
        return new ChoosePatientController(choosepatientInteractor);
    }

    private static SwapToWelcomeController createSwapToWelcomeUseCase(ViewManagerModel viewManagerModel,
                                                                          WelcomeViewModel welcomeViewModel) {
        SwapToWelcomeOutputBoundary welcomePresenter = new SwapToWelcomePresenter(viewManagerModel, welcomeViewModel);
        SwapToWelcomeInputBoundary swapToWelcomeInteractor = new SwapToWelcomeInteractor(welcomePresenter);
        return new SwapToWelcomeController(swapToWelcomeInteractor);
    }

    private static LoadPatientsController createLoadPatientsUseCase(DAOFacade userDao){
        LoadPatientInputBoundary loadPatientInteractor = new LoadPatientInteractor(userDao);
        return new LoadPatientsController(loadPatientInteractor);
    }

    private static SwapToDoctorUpdateController createSwapToDoctorUpdateController(ViewManagerModel viewManagerModel,
                                                                                   DoctorUpdateViewModel doctorUpdateViewModel){
        SwapToDoctorUpdateOutputBoundary swapToDoctorUpdatePresenter = new SwapToDoctorUpdatePresenter(viewManagerModel, doctorUpdateViewModel);
        SwapToDoctorUpdateInputBoundary swapToDoctorUpdateInteractor= new SwapToDoctorUpdateInteractor(swapToDoctorUpdatePresenter);
        return new SwapToDoctorUpdateController(swapToDoctorUpdateInteractor);
    }
}
