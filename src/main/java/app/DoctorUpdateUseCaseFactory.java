package app;

import data_access.DAOFacade;
import entity.people.DoctorUserFactory;
import interface_adapter.ViewManagerModel;
import interface_adapter.choosepatient.ChoosePatientViewModel;
import interface_adapter.login.LoginViewModel;
import interface_adapter.swap_views.list_of_patients.SwapToPatientListController;
import interface_adapter.swap_views.list_of_patients.SwapToPatientListPresenter;
import interface_adapter.update.doctor.DoctorUpdateController;
import interface_adapter.update.doctor.DoctorUpdatePresenter;
import interface_adapter.update.doctor.DoctorUpdateViewModel;
import use_case.swap_views.list_of_patients.SwapToPatientListInputBoundary;
import use_case.swap_views.list_of_patients.SwapToPatientListInteractor;
import use_case.swap_views.list_of_patients.SwapToPatientListOutputBoundary;
import use_case.update.doctor.DoctorUpdateInputBoundary;
import use_case.update.doctor.DoctorUpdateInteractor;
import use_case.update.doctor.DoctorUpdateOutputBoundary;
import view.DoctorUpdateView;

public class DoctorUpdateUseCaseFactory {

    /* Prevent instantiation. */
    public DoctorUpdateUseCaseFactory() { }

    public static DoctorUpdateView create(DAOFacade daoFacade,
                                          ViewManagerModel viewManagerModel,
                                          DoctorUpdateViewModel doctorUpdateViewModel,
                                          ChoosePatientViewModel choosePatientViewModel,
                                          LoginViewModel loginViewModel) {
        SwapToPatientListController swapToPatientListController = createSwapToPatientListUseCase(viewManagerModel, choosePatientViewModel);
        DoctorUpdateController updateController = createDoctorUpdateUseCase(daoFacade, doctorUpdateViewModel, loginViewModel, viewManagerModel);
        return new DoctorUpdateView(doctorUpdateViewModel, swapToPatientListController, updateController);
    }

    private static DoctorUpdateController createDoctorUpdateUseCase(DAOFacade daoFacade, DoctorUpdateViewModel doctorUpdateViewModel, LoginViewModel loginViewModel, ViewManagerModel viewManagerModel) {
        DoctorUpdateOutputBoundary doctorUpdatePresenter = new DoctorUpdatePresenter(doctorUpdateViewModel, loginViewModel, viewManagerModel);
        DoctorUserFactory docFactory = new DoctorUserFactory();
        DoctorUpdateInputBoundary doctorInteractor = new DoctorUpdateInteractor(daoFacade, doctorUpdatePresenter, docFactory);
        return new DoctorUpdateController(doctorInteractor);
    }

    private static SwapToPatientListController createSwapToPatientListUseCase(ViewManagerModel viewManagerModel, ChoosePatientViewModel choosePatientViewModel) {
        SwapToPatientListOutputBoundary toPatientListPresenter = new SwapToPatientListPresenter(viewManagerModel, choosePatientViewModel);
        SwapToPatientListInputBoundary toPatientListInteractor = new SwapToPatientListInteractor(toPatientListPresenter);
        return new SwapToPatientListController(toPatientListInteractor);
    }
}
