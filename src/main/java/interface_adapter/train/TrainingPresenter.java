package interface_adapter.train;

import interface_adapter.ViewManagerModel;
import use_case.chatbot.DialogflowOutputBoundary;
import use_case.chatbot.DialogflowOutputData;


public class TrainingPresenter implements TrainingOutputBoundary {

    private final TrainingViewModel viewModel;
    private final ViewManagerModel viewManagerModel;

    public TrainingPresenter(ViewManagerModel viewManagerModel, TrainingViewModel viewModel) {
        this.viewManagerModel = viewManagerModel;
        this.viewModel = viewModel;
    }

    @Override
    public void prepareSuccessView(TrainingOutputData outputData) {
        TrainingState dialogflowState = viewModel.getState();
        this.viewModel.setState(dialogflowState);
        this.viewModel.firePropertyChanged();

        this.viewManagerModel.setActiveView(viewModel.getViewName());
        this.viewManagerModel.firePropertyChanged();
    }

    @Override
    public void prepareFailView(String error) {

    }
}
