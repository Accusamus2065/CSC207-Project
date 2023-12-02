package use_case.chat.save;

import data_access.ConvoDAOImpl;

public class ConversationSaveInteractor implements ConversationSaveInputBoundary{
    final ConvoDAOImpl dao;

    public ConversationSaveInteractor(ConvoDAOImpl convDao){
       this.dao = convDao;
    }
    @Override
    public void executeSave(ConversationSaveInputData data) {
        dao.save(data.getMessage());    // no output TODO: no output for save message
    }
}
