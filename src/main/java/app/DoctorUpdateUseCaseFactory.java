package app;

import data_access.DAOFacade;
import entity.people.DoctorUserFactory;
import interface_adapter.ViewManagerModel;
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

    public static DoctorUpdateView create(DAOFacade daoFacade, DoctorUpdateViewModel doctorUpdateViewModel) {
        SwapToPatientListController swapToPatientListController = createSwapToPatientListUseCase();
        DoctorUpdateController updateController = createDoctorUpdateUseCase(daoFacade);
        return new DoctorUpdateView(doctorUpdateViewModel, swapToPatientListController, updateController);
    }

    private static DoctorUpdateController createDoctorUpdateUseCase(DAOFacade daoFacade) {
        DoctorUpdateOutputBoundary doctorUpdatePresenter = new DoctorUpdatePresenter(new DoctorUpdateViewModel());
        DoctorUserFactory docFactory = new DoctorUserFactory();
        DoctorUpdateInputBoundary doctorInteractor = new DoctorUpdateInteractor(daoFacade, doctorUpdatePresenter, docFactory);
        return new DoctorUpdateController(doctorInteractor);
    }

    private static SwapToPatientListController createSwapToPatientListUseCase() {
        SwapToPatientListOutputBoundary toPatientListPresenter = new SwapToPatientListPresenter();
        SwapToPatientListInputBoundary toPatientListInteractor = new SwapToPatientListInteractor(toPatientListPresenter);
        return new SwapToPatientListController(toPatientListInteractor);
    }
}
