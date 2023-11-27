package interface_adapter.swap_views.update.doctor;

import interface_adapter.ViewManagerModel;
import interface_adapter.update.doctor.DoctorUpdateViewModel;
import use_case.swap_views.update.doctor.SwapToDoctorUpdateOutputBoundary;

public class SwapToDoctorUpdatePresenter implements SwapToDoctorUpdateOutputBoundary {
    ViewManagerModel viewManagerModel;
    DoctorUpdateViewModel doctorUpdateViewModel;

    public SwapToDoctorUpdatePresenter(ViewManagerModel viewManagerModel, DoctorUpdateViewModel doctorUpdateViewModel) {
        this.viewManagerModel = viewManagerModel;
        this.doctorUpdateViewModel = doctorUpdateViewModel;
    }

    @Override
    public void execute() {
        viewManagerModel.setActiveView(doctorUpdateViewModel.getViewName());
        viewManagerModel.firePropertyChanged();
    }
}
