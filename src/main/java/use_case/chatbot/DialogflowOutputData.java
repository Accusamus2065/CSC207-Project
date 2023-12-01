package use_case.chatbot;

import entity.chat.Message;

import java.util.List;

public class DialogflowOutputData {
    private String response;

    public DialogflowOutputData(String response) {
        this.response = response;
    }

    public String getResponse() {
        return response;
    }
}
