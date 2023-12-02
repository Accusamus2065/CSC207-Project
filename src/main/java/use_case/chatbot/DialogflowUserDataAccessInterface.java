package use_case.chatbot;


import java.util.List;

public interface DialogflowUserDataAccessInterface {
    List<Object> detectIntent(String userInput);
}
