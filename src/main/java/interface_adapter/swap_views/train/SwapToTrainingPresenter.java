package interface_adapter.swap_views.train;

import interface_adapter.ViewManagerModel;
import interface_adapter.train.TrainingState;
import interface_adapter.train.TrainingViewModel;
import use_case.swap_views.train.SwapToTrainingOutputBoundary;

public class SwapToTrainingPresenter implements SwapToTrainingOutputBoundary {
    ViewManagerModel viewManagerModel;
    TrainingViewModel trainingViewModel;

    public SwapToTrainingPresenter(ViewManagerModel viewManagerModel, TrainingViewModel trainingViewModel) {
        this.viewManagerModel = viewManagerModel;
        this.trainingViewModel = trainingViewModel;
    }


    @Override
    public void execute() {
        TrainingState state = trainingViewModel.getState();
        state.setError(null);
        viewManagerModel.setActiveView(trainingViewModel.getViewName());
        viewManagerModel.firePropertyChanged();
    }
}
