package interface_adapter.chat.save;

import use_case.chat.save.ConversationSaveInputBoundary;
import use_case.chat.save.ConversationSaveInputData;

public class ConversationSaveController {
    final ConversationSaveInputBoundary conversationSaveUseCaseInteractor;

    public ConversationSaveController(ConversationSaveInputBoundary conversationSaveUseCaseInteractor) {
        this.conversationSaveUseCaseInteractor = conversationSaveUseCaseInteractor;
    }

    public void executeSave(String sender, String receiver, String messageContent) {
        ConversationSaveInputData data = new ConversationSaveInputData(sender, receiver, messageContent);
        conversationSaveUseCaseInteractor.executeSave(data);
    }
}
