package use_case.chat.save;

import data_access.ConvoDAOImpl;
import use_case.chat.ConversationUserDataAccessInterface;

public class ConversationSaveInteractor implements ConversationSaveInputBoundary{
    final ConversationUserDataAccessInterface dao;

    public ConversationSaveInteractor(ConversationUserDataAccessInterface convDao){
       this.dao = convDao;
    }
    @Override
    public void executeSave(ConversationSaveInputData data) {
        dao.save(data.getMessage());    // no output TODO: no output for save message
    }
}
