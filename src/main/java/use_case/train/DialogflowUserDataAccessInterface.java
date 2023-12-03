package use_case.train;


import java.util.List;

public interface DialogflowUserDataAccessInterface {
    List<Object> detectIntent(String userInput);
}
