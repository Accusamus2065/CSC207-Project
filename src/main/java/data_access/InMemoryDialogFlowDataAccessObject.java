package data_access;

import use_case.chatbot.DialogflowUserDataAccessInterface;
import use_case.train.TrainingUserDataAccessInterface;

import java.util.HashMap;
import java.util.List;

public class InMemoryDialogFlowDataAccessObject implements
        TrainingUserDataAccessInterface,
        DialogflowUserDataAccessInterface {
    public HashMap<String, List<Object>> intents = new HashMap<>();
    public HashMap<List<String>, List<Object>> responses = new HashMap<>();
    public final String USERNAME;

    public InMemoryDialogFlowDataAccessObject(String username) {
        USERNAME = username;
    }


    @Override
    public List<Object> detectIntent(String userInput) {
        return responses.get(List.of(userInput, USERNAME));
    }

    @Override
    public void setIntentNEntities(String intentName, List<String> phrases, List<String> messages) {
        List<Object> phrasesAndMessages = List.of(phrases, messages);
        intents.put(intentName, phrasesAndMessages);
    }
}
