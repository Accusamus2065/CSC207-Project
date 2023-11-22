package interface_adapter.update.patient;

import interface_adapter.ViewManagerModel;
import interface_adapter.login.LoginState;
import interface_adapter.login.LoginViewModel;
import use_case.update.patient.PatientUpdateOutputBoundary;
import use_case.update.patient.PatientUpdateOutputData;

public class PatientUpdatePresenter implements PatientUpdateOutputBoundary {
    private final LoginViewModel loginViewModel;
    private final PatientUpdateViewModel patientUpdateViewModel;
    private final ViewManagerModel viewManagerModel;

    public PatientUpdatePresenter(LoginViewModel loginViewModel,
                                  PatientUpdateViewModel patientUpdateViewModel,
                                  ViewManagerModel viewManagerModel) {
        this.loginViewModel = loginViewModel;
        this.patientUpdateViewModel = patientUpdateViewModel;
        this.viewManagerModel = viewManagerModel;
    }

    @Override
    public void prepareSuccessView(PatientUpdateOutputData response) {
        assert !response.isUseCaseFailed();
        System.out.println("Update successful: username" + response.getUsername());

        LoginState loginState = loginViewModel.getState();
        loginState.setUsername(response.getUsername());
        this.loginViewModel.setState(loginState);
        loginViewModel.firePropertyChanged();

        viewManagerModel.setActiveView(loginViewModel.getViewName());
        viewManagerModel.firePropertyChanged();
    }

    @Override
    public void prepareFailView(String error) {

    }
}
