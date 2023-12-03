package use_case.train;

import java.util.List;

public class TrainingInputData {
    private final String intent;
    private final List<String> phrases;
    private final List<String> messages;

    public TrainingInputData(String intent, List<String> phrases, List<String> messages) {
        this.intent = intent;
        this.phrases = phrases;
        this.messages = messages;
    }

    public String getIntent() {
        return intent;
    }
    public List<String> getPhrases() {
        return phrases;
    }
    public List<String> getMessages() {
        return messages;
    }
}
