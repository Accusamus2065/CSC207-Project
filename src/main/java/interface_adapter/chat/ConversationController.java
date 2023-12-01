package interface_adapter.chat;

import entity.people.User;
import use_case.chat.ConversationInputBoundary;
import use_case.chat.ConversationInputData;
import view.ConversationView;

public class ConversationController {

    final ConversationInputBoundary conversationUseCaseInteractor;
    public ConversationController(ConversationInputBoundary conversationUseCaseInteractor) {
        this.conversationUseCaseInteractor = conversationUseCaseInteractor;
    }

    public void executeSave(String sender, String receiver, String messageContent) {
        ConversationInputData data = new ConversationInputData(sender, receiver, messageContent);
        conversationUseCaseInteractor.executeSave(data);
    }

    public void executeRefresh(String sender, String receiver) {
        ConversationInputData data = new ConversationInputData(sender, receiver, null);
        conversationUseCaseInteractor.executeRefresh(data);
    }
}
