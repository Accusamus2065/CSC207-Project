package use_case.chatbot;

public class DialogflowOutputData {
    private final String response;

    public DialogflowOutputData(String response) {
        this.response = response;
    }

    public String getResponse() {
        return response;
    }
}
