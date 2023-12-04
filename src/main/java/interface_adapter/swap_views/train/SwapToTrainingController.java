package interface_adapter.swap_views.train;

import use_case.swap_views.train.SwapToTrainingInputBoundary;

public class SwapToTrainingController {
    SwapToTrainingInputBoundary swapToTrainingInteractor;

    public SwapToTrainingController(SwapToTrainingInputBoundary swapToTrainingInteractor) {
        this.swapToTrainingInteractor = swapToTrainingInteractor;
    }

    public void execute() {
        swapToTrainingInteractor.execute();
    }
}
