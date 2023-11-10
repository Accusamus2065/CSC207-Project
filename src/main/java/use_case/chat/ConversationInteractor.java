package use_case.chat;

import data_access.ConvoDAOImpl;

public class ConversationInteractor implements ConversationInputBoundary {
    final ConversationOutputBoundary presenter;
    final ConvoDAOImpl dao;

    public ConversationInteractor(ConvoDAOImpl convDao,
                                  ConversationOutputBoundary presenter) {
        this.dao = convDao;
        this.presenter = presenter;
    }

    @Override
    public void execute(ConversationInputData data) {

    }
}