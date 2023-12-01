package use_case.chatbot;

public class DialogflowOutputData {
    private String response;
    private String username;

    public DialogflowOutputData(String response, String username) {
        this.response = response;
        this.username = username;
    }

    public String getResponse() {
        return response;
    }
    public String getUsername() { return username;}
}
