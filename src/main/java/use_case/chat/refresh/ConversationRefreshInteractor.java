//package use_case.chat.refresh;
//
//import data_access.ConvoDAOImpl;
//import entity.chat.Message;
//import use_case.chat.ConversationUserDataAccessInterface;
//import use_case.chat.save.ConversationSaveInteractor;
//
//import java.util.List;
//
//public class ConversationRefreshInteractor implements ConversationRefreshInputBoundary {
//    final ConversationRefreshOutputBoundary presenter;
//    final ConversationUserDataAccessInterface dao;
//
//    public ConversationRefreshInteractor(ConversationUserDataAccessInterface convDao,
//                                         ConversationRefreshOutputBoundary presenter) {
//        this.dao = convDao;
//        this.presenter = presenter;
//    }
//    @Override
//    public void executeRefresh(ConversationRefreshInputData data) {
//        List<Message> messages = dao.query(data.getMessage().getSender(), data.getMessage().getReceiver());
//        ConversationRefreshOutputData outputData = new ConversationRefreshOutputData(messages);
//        presenter.prepareSuccessView(outputData);
//    }
//
//}

package use_case.chat.refresh;

import entity.chat.Message;
import use_case.chat.ConversationUserDataAccessInterface;

import java.util.List;

/**
 * The ConversationRefreshInteractor class represents the interactor for the conversation refresh use case.
 * It interacts with the data access layer and prepares the output data for presentation through the output boundary.
 */
public class ConversationRefreshInteractor implements ConversationRefreshInputBoundary {

    // The output boundary for presenting the results of the conversation refresh use case
    final ConversationRefreshOutputBoundary presenter;

    // The data access interface for interacting with the conversation data
    final ConversationUserDataAccessInterface dao;

    /**
     * Constructor for ConversationRefreshInteractor.
     *
     * @param convDao   The conversation data access interface.
     * @param presenter The output boundary for presenting the results.
     */
    public ConversationRefreshInteractor(ConversationUserDataAccessInterface convDao,
                                         ConversationRefreshOutputBoundary presenter) {
        this.dao = convDao;
        this.presenter = presenter;
    }

    /**
     * Executes the refresh operation for a conversation based on the provided input data.
     *
     * @param data The input data containing information necessary for the refresh operation.
     */
    @Override
    public void executeRefresh(ConversationRefreshInputData data) {
        // Query the conversation data based on sender and receiver
        List<Message> messages = dao.query(data.getMessage().getSender(), data.getMessage().getReceiver());

        // Prepare the output data with the retrieved messages
        ConversationRefreshOutputData outputData = new ConversationRefreshOutputData(messages);

        // Present the success view with the output data
        presenter.prepareSuccessView(outputData);
    }
}
