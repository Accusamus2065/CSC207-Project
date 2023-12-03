package interface_adapter.train;

import java.util.List;

public class TrainingState {

    private String intent;
    private String error;

    public TrainingState(TrainingState copy) {
        intent = copy.intent;
        error = copy.error;
    }

    // Because of the previous copy constructor, the default constructor must be explicit.
    public TrainingState() {
    }

    public void setError(String error) {
        this.error = error;
    }

    public String getError() {
        return error;
    }

    public String getIntent() {
        return intent;
    }

    public void setIntent(String intent) {
        this.intent = intent;
    }
}

