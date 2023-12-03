//package use_case.chat.save;
//
//import data_access.ConvoDAOImpl;
//import use_case.chat.ConversationUserDataAccessInterface;
//
//public class ConversationSaveInteractor implements ConversationSaveInputBoundary{
//    final ConversationUserDataAccessInterface dao;
//
//    public ConversationSaveInteractor(ConversationUserDataAccessInterface convDao){
//       this.dao = convDao;
//    }
//    @Override
//    public void executeSave(ConversationSaveInputData data) {
//        dao.save(data.getMessage());    // no output TODO: no output for save message
//    }
//}

package use_case.chat.save;
import use_case.chat.ConversationUserDataAccessInterface;

/**
 * The ConversationSaveInteractor class represents the interactor for the conversation save use case.
 * It interacts with the data access layer to execute the save operation for a conversation message.
 */
public class ConversationSaveInteractor implements ConversationSaveInputBoundary {

    // The data access interface for interacting with the conversation data
    final ConversationUserDataAccessInterface dao;

    /**
     * Constructor for ConversationSaveInteractor.
     *
     * @param convDao The conversation data access interface.
     */
    public ConversationSaveInteractor(ConversationUserDataAccessInterface convDao) {
        this.dao = convDao;
    }

    /**
     * Executes the save operation for a conversation based on the provided input data.
     *
     * @param data The input data containing information necessary for the save operation.
     */
    @Override
    public void executeSave(ConversationSaveInputData data) {
        // Save the conversation message through the data access interface
        dao.save(data.getMessage());
        // No output for save operation, hence no return value or output data
    }
}
