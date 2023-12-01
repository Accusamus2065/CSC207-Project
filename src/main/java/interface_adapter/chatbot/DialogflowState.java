package interface_adapter.chatbot;

import entity.chat.Message;

import java.util.List;

public class DialogflowState {

    private String response;
    private String username;

    public DialogflowState(DialogflowState copy) {
        response = copy.response;
    }

    // Because of the previous copy constructor, the default constructor must be explicit.
    public DialogflowState() {
    }

    public String getResponse() {
        return response;
    }

    public void setResponse(String response) {
        this.response = response;
    }
    public void setUsername(String username) {
        this.username = username;
    }
    public String getUsername() {
        return username;
    }
}
