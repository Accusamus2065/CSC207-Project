package app;

import data_access.InMemoryUserDataAccessObject;
import interface_adapter.ViewManagerModel;
import interface_adapter.choose_patient.ChoosePatientViewModel;
import interface_adapter.login.LoginViewModel;
import interface_adapter.update.doctor.DoctorUpdateViewModel;
import org.junit.Test;
import view.DoctorUpdateView;


import static org.junit.Assert.*;

public class DoctorUpdateUseCaseFactoryTest {
    @Test
    public void executeUseCase() {
        ViewManagerModel viewManagerModel = new ViewManagerModel();
        DoctorUpdateViewModel doctorUpdateViewModel = new DoctorUpdateViewModel();
        ChoosePatientViewModel choosePatientViewModel = new ChoosePatientViewModel();
        LoginViewModel loginViewModel = new LoginViewModel();
        InMemoryUserDataAccessObject dataAccessObject = new InMemoryUserDataAccessObject();

        DoctorUpdateView doctorUpdateView = DoctorUpdateUseCaseFactory.create(
                dataAccessObject,
                viewManagerModel,
                doctorUpdateViewModel,
                choosePatientViewModel,
                loginViewModel
        );
        assertEquals(doctorUpdateViewModel.getViewName(), doctorUpdateView.viewName);
    }
}