package data_access;

import use_case.train.TrainingUserDataAccessInterface;

import java.util.HashMap;
import java.util.List;

public class InMemoryDialogFlowDataAccessObject implements TrainingUserDataAccessInterface {
    public HashMap<String, List<List<String>>> intents = new HashMap<>();

    @Override
    public List<Object> detectIntent(String userInput) {
        return null;
    }

    @Override
    public void setIntentNEntities(String intentName, List<String> phrases, List<String> messages) {
        List<List<String>> phrasesAndMessages = List.of(phrases, messages);
        intents.put(intentName, phrasesAndMessages);
    }
}
