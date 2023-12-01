package use_case.chat.save;

import use_case.chat.ConversationInputData;

public interface ConversationSaveInputBoundary {
    void executeSave(ConversationSaveInputData data);
}
