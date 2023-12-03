package use_case.chatbot;

import java.util.List;

public class DialogflowOutputData {
    private String response;
    private String username;
    private List<String> docNames;

    public DialogflowOutputData(String response, String username, List<String> docNames) {
        this.response = response;
        this.username = username;
        this.docNames = docNames;
    }

    public String getResponse() {
        return response;
    }
    public String getUsername() { return username;}

    public List<String> getDocNames() { return docNames; }

}
