package interface_adapter.chatbot;

import entity.chat.Message;

import java.util.List;

public class DialogflowState {

    private String response;

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

}
