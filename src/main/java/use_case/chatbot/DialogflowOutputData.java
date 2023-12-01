package use_case.chatbot;

import entity.chat.Message;

import java.util.List;

public class DialogflowOutputData {
    private String response;
    private String username;
    public DialogflowOutputData(String response) {
        this.response = response;
        this.username = username;
    }


    public String getResponse() {
        return response;
    }
    public String getUsername() { return username;}
}
