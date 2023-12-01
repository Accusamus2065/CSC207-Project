package interface_adapter.chatbot;
import use_case.chatbot.DialogflowInputBoundary;
import use_case.chatbot.DialogflowInputData;

public class DialogflowController {

    final DialogflowInputBoundary dialogflowInputBoundary;
    public DialogflowController(DialogflowInputBoundary dialogflowUseCaseInteractor) {
        this.dialogflowInputBoundary = dialogflowUseCaseInteractor;
    }

    public void execute(String messageContent, String username) {
        DialogflowInputData data = new DialogflowInputData(messageContent, username);
        dialogflowInputBoundary.execute(data);
    }
}
