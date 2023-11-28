package interface_adapter.swap_views.list_of_patients;

import interface_adapter.ViewManagerModel;
import interface_adapter.choosepatient.ChoosePatientViewModel;
import use_case.swap_views.list_of_patients.SwapToPatientListOutputBoundary;

public class SwapToPatientListPresenter implements SwapToPatientListOutputBoundary {
    public final ViewManagerModel viewManagerModel;
    public final ChoosePatientViewModel choosePatientViewModel;

    public SwapToPatientListPresenter(ViewManagerModel viewManagerModel, ChoosePatientViewModel choosePatientViewModel) {
        this.viewManagerModel = viewManagerModel;
        this.choosePatientViewModel = choosePatientViewModel;
    }

    @Override
    public void swapViews() {
        viewManagerModel.setActiveView(choosePatientViewModel.getViewName());
        viewManagerModel.firePropertyChanged();
    }
}
