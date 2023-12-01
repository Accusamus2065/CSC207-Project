package use_case.chat.save;

import data_access.ConvoDAOImpl;
import use_case.chat.ConversationInputData;
import use_case.chat.ConversationOutputBoundary;

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
