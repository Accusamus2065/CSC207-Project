package interface_adapter.train;

import java.util.List;

public class TrainingState {

    private String intent;

    public TrainingState(TrainingState copy) {
        intent = copy.intent;
    }

    // Because of the previous copy constructor, the default constructor must be explicit.
    public TrainingState() {
    }

    public String getIntent() {
        return intent;
    }

    public void setIntent(String intent) {
        this.intent = intent;
    }
}

