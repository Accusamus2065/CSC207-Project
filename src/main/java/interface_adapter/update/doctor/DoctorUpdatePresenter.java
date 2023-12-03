package interface_adapter.update.doctor;

import interface_adapter.ViewManagerModel;
import interface_adapter.login.LoginState;
import interface_adapter.login.LoginViewModel;
import use_case.update.doctor.DoctorUpdateOutputBoundary;
import use_case.update.doctor.DoctorUpdateOutputData;

public class DoctorUpdatePresenter implements DoctorUpdateOutputBoundary {
    private final DoctorUpdateViewModel doctorUpdateViewModel;
    private final LoginViewModel loginViewModel;
    private final ViewManagerModel viewManagerModel;

    public DoctorUpdatePresenter(DoctorUpdateViewModel doctorUpdateViewModel,
                                 LoginViewModel loginViewModel,
                                 ViewManagerModel viewManagerModel) {
        this.doctorUpdateViewModel = doctorUpdateViewModel;
        this.loginViewModel = loginViewModel;
        this.viewManagerModel = viewManagerModel;
    }

    @Override
    public void prepareSuccessView(DoctorUpdateOutputData response) {
        assert !response.isUseCaseFailed();
        System.out.println("Updated doctor details");

        LoginState loginState = loginViewModel.getState();
        DoctorUpdateState state = doctorUpdateViewModel.getState();
        state.setError(null);
        loginState.setError(null);
        loginState.setUsername(response.getUsername());
        this.loginViewModel.setState(loginState);
        loginViewModel.firePropertyChanged();

        viewManagerModel.setActiveView(loginViewModel.getViewName());
        viewManagerModel.firePropertyChanged();
    }

    @Override
    public void prepareFailView(String error) {
        DoctorUpdateState doctorUpdateState = doctorUpdateViewModel.getState();
        doctorUpdateState.setError(error);
        doctorUpdateState.setNewUsername("");
        doctorUpdateState.setPassword("");
        doctorUpdateState.setRepeatPassword("");
        doctorUpdateState.setSpecialty("");
        doctorUpdateState.setDegree("");
        doctorUpdateViewModel.firePropertyChanged();
    }
}
