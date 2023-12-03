package interface_adapter.train;

import java.util.List;

public class TrainingController {

    final TrainingInputBoundary interactor;
    public TrainingController(TrainingInputBoundary interactor) {
        this.interactor = interactor;
    }

    public void execute(String intent, List<String> phrases, List<String> messages) {
        TrainingInputData data = new TrainingInputData(intent, phrases, messages);
        interactor.execute(data);
    }
}
