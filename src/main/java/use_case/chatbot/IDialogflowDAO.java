package use_case.chatbot;


import entity.chat.Message;

import java.util.List;

public interface IDialogflowDAO {
    String detectIntent(String userInput);
}
