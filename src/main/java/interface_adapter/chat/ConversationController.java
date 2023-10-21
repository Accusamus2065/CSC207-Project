package interface_adapter.chat;

import entity.people.User;

public class ConversationController {

    final ConversationInputBoundary conversationUseCaseInteractor;
    public ConversationController(ConversationInputBoundary conversationUseCaseInteractor) {
        this.conversationUseCaseInteractor = conversationUseCaseInteractor;
    }

    public void execute(String messageContent, User user1, User user2) {
        ConversationInputData data = new ConversationInputData(messageContent, user1, user2);
        userSignupUseCaseInteractor.execute(data);
    }
}
