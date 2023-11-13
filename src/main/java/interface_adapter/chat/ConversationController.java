package interface_adapter.chat;

import entity.people.User;
import use_case.chat.ConversationInputBoundary;
import use_case.chat.ConversationInputData;

public class ConversationController {

    final ConversationInputBoundary conversationUseCaseInteractor;
    public ConversationController(ConversationInputBoundary conversationUseCaseInteractor) {
        this.conversationUseCaseInteractor = conversationUseCaseInteractor;
    }

    public void execute(String user1, String user2, String messageContent) {
        ConversationInputData data = new ConversationInputData(user1, user2, messageContent);
        conversationUseCaseInteractor.execute(data);
    }
}
