package app;

import data_access.DAOFacade;
import data_access.PatientDAOImpl;
import entity.people.DoctorUserFactory;
import entity.people.PatientUserFactory;
import interface_adapter.ViewManagerModel;
import interface_adapter.chat.ConversationViewModel;
import interface_adapter.choosepatient.ChoosePatientController;
import interface_adapter.choosepatient.ChoosePatientPresenter;
import interface_adapter.choosepatient.ChoosePatientViewModel;
import interface_adapter.swap_views.list_of_patients.SwapToPatientListController;
import interface_adapter.swap_views.list_of_patients.SwapToPatientListPresenter;
import interface_adapter.update.doctor.DoctorUpdateController;
import interface_adapter.update.doctor.DoctorUpdatePresenter;
import interface_adapter.update.doctor.DoctorUpdateViewModel;
import interface_adapter.welcome.WelcomeViewModel;
import use_case.choosepatient.ChoosePatientInputData;
import use_case.choosepatient.ChoosePatientInteractor;
import use_case.choosepatient.ChoosePatientOutputBoundary;
import use_case.choosepatient.ChoosePatientUserDataAccessInterface;
import use_case.swap_views.list_of_patients.SwapToPatientListInputBoundary;
import use_case.swap_views.list_of_patients.SwapToPatientListInteractor;
import use_case.swap_views.list_of_patients.SwapToPatientListOutputBoundary;
import use_case.update.doctor.DoctorUpdateInputBoundary;
import use_case.update.doctor.DoctorUpdateInteractor;
import use_case.update.doctor.DoctorUpdateOutputBoundary;
import view.DoctorUpdateView;
import view.ListOfPatientsView;

public class ChoosePatientUseCaseFactory {

    /* Prevent instantiation. */
    public ChoosePatientUseCaseFactory() { }

    public static ListOfPatientsView create(DAOFacade daoFacade, DoctorUpdateViewModel doctorUpdateViewModel) {
        SwapToPatientListController swapToPatientListController = createSwapToPatientListUseCase();
        ChoosePatientController updateController = createChoosePatientUseCase(daoFacade);
        return new ListOfPatientsView(doctorUpdateViewModel, swapToPatientListController, updateController);
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
        ChoosePatientInputData choosepatientInteractor = new ChoosePatientInteractor(choosepatientPresenter);
        return new DoctorUpdateController(doctorInteractor);
    }

    private static SwapToPatientListController createSwapToPatientListUseCase() {
        SwapToPatientListOutputBoundary toPatientListPresenter = new SwapToPatientListPresenter();
        SwapToPatientListInputBoundary toPatientListInteractor = new SwapToPatientListInteractor(toPatientListPresenter);
        return new SwapToPatientListController(toPatientListInteractor);
    }
}
