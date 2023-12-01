package use_case.chat;

import view.ConversationView;

public interface ConversationInputBoundary {
    void executeSave(ConversationInputData data);
    void executeRefresh(ConversationInputData data);

}
