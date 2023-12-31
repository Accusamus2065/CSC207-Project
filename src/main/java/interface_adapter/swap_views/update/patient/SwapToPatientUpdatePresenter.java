package interface_adapter.swap_views.update.patient;

import interface_adapter.ViewManagerModel;
import interface_adapter.update.patient.PatientUpdateState;
import interface_adapter.update.patient.PatientUpdateViewModel;
import use_case.swap_views.update.patient.SwapToPatientUpdateOutputBoundary;

public class SwapToPatientUpdatePresenter implements SwapToPatientUpdateOutputBoundary {
    ViewManagerModel viewManagerModel;
    PatientUpdateViewModel patientUpdateViewModel;

    public SwapToPatientUpdatePresenter(ViewManagerModel viewManagerModel, PatientUpdateViewModel patientUpdateViewModel) {
        this.viewManagerModel = viewManagerModel;
        this.patientUpdateViewModel = patientUpdateViewModel;
    }

    @Override
    public void execute(String username) {
        PatientUpdateState state = patientUpdateViewModel.getState();
        state.setUsername(username);
        state.setError(null);
        state.setNewUsername("");
        state.setPassword("");
        state.setRepeatPassword("");
        state.setSex("");
        state.setGender("");
        state.setHeight(0);
        state.setWeight(0);
        state.setBloodType("");
        this.patientUpdateViewModel.setState(state);
        patientUpdateViewModel.firePropertyChanged();

        viewManagerModel.setActiveView(patientUpdateViewModel.getViewName());
        viewManagerModel.firePropertyChanged();
    }
}
