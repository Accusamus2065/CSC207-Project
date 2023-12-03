package use_case.train;


import java.util.List;

public interface TrainingUserDataAccessInterface {
    List<Object> detectIntent(String userInput);

    void setIntentNEntities(String intentName, List<String> phrases, List<String> messages);
}
