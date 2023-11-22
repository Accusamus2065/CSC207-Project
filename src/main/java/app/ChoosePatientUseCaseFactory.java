package app;

import data_access.DAOFacade;

import entity.people.PatientUserFactory;
import interface_adapter.ViewManagerModel;
import interface_adapter.chat.ConversationViewModel;
import interface_adapter.choosepatient.ChoosePatientController;
import interface_adapter.choosepatient.ChoosePatientPresenter;
import interface_adapter.choosepatient.ChoosePatientViewModel;

import interface_adapter.update.doctor.DoctorUpdateViewModel;
import interface_adapter.welcome.WelcomeViewModel;
import use_case.choosepatient.*;
import view.ListOfPatientsView;

public class ChoosePatientUseCaseFactory {

    /* Prevent instantiation. */
    public ChoosePatientUseCaseFactory() { }

    public static ListOfPatientsView create(ViewManagerModel viewManagerModel,
                                            ConversationViewModel conversationViewModel,
                                            WelcomeViewModel welcomeViewModel,
                                            DoctorUpdateViewModel doctorUpdateViewModel,
                                            ChoosePatientViewModel choosePatientViewModel) {
        ChoosePatientController updateController = createChoosePatientUseCase(viewManagerModel, conversationViewModel, welcomeViewModel, doctorUpdateViewModel, choosePatientViewModel);
        return new ListOfPatientsView(updateController, choosePatientViewModel);
    }

    private static ChoosePatientController createChoosePatientUseCase(ViewManagerModel viewManagerModel,
                                                                      ConversationViewModel conversationViewModel,
                                                                      WelcomeViewModel welcomeViewModel,
                                                                      DoctorUpdateViewModel doctorUpdateViewModel,
                                                                      ChoosePatientViewModel choosePatientViewModel) {
        ChoosePatientOutputBoundary choosepatientPresenter = new ChoosePatientPresenter(
                                                                                        viewManagerModel,
                                                                                        conversationViewModel,
                                                                                        welcomeViewModel,
                                                                                        doctorUpdateViewModel,
                                                                                        choosePatientViewModel);
        PatientUserFactory patientUserFactory = new PatientUserFactory();
        ChoosePatientUserDataAccessInterface patientDAO = new DAOFacade();
        ChoosePatientInputBoundary choosepatientInteractor = new ChoosePatientInteractor(patientDAO, choosepatientPresenter);
        return new ChoosePatientController(choosepatientInteractor);
    }

    // I dont think i need this but might need in the future
//    private static SwapToPatientListController createSwapToPatientListUseCase() {
//        SwapToPatientListOutputBoundary toPatientListPresenter = new SwapToPatientListPresenter();
//        SwapToPatientListInputBoundary toPatientListInteractor = new SwapToPatientListInteractor(toPatientListPresenter);
//        return new SwapToPatientListController(toPatientListInteractor);
//    }
}
