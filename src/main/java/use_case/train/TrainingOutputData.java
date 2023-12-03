package use_case.train;

import java.util.List;

public class TrainingOutputData {
    private String intent;

    public TrainingOutputData(String intent) {
        this.intent = intent;
    }

    public String getIntent() {
        return intent;
    }

}
