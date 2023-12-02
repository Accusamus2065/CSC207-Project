package use_case.chatbot;


import java.util.List;
<<<<<<< Updated upstream

public interface DialogflowUserDataAccessInterface {
    List<Object> detectIntent(String userInput);
=======
import java.util.Map;

public interface DialogflowUserDataAccessInterface {
    Map<String, List<String>> detectIntent(String userInput);
    List<String> getDocNames(String intent);
>>>>>>> Stashed changes
}
