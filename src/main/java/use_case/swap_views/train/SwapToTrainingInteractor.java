package use_case.swap_views.train;

public class SwapToTrainingInteractor implements SwapToTrainingInputBoundary {
    SwapToTrainingOutputBoundary swapToTrainingPresenter;

    public SwapToTrainingInteractor(SwapToTrainingOutputBoundary swapToTrainingPresenter) {
        this.swapToTrainingPresenter = swapToTrainingPresenter;
    }

    @Override
    public void execute() {
        swapToTrainingPresenter.execute();
    }
}
