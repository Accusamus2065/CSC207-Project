package use_case.chat;

import data_access.ConvoDAOImpl;
import entity.chat.Message;
import interface_adapter.chat.ConversationController;
import view.ConversationView;

import java.util.List;
import java.util.concurrent.CompletableFuture;

public class ConversationInteractor implements ConversationInputBoundary {
    final ConversationOutputBoundary presenter;
    final ConvoDAOImpl dao;

    public ConversationInteractor(ConvoDAOImpl convDao,
                                  ConversationOutputBoundary presenter) {
        this.dao = convDao;
        this.presenter = presenter;
    }

    @Override
    public void executeSave(ConversationInputData data) {
        dao.save(data.getMessage());    // no output TODO: no output for save message
    }

    @Override
    public void executeRefresh(ConversationInputData data) {
        List<Message> messages = dao.query(data.getMessage().getSender(), data.getMessage().getReceiver());
        ConversationOutputData outputData = new ConversationOutputData(messages);
        presenter.prepareSuccessView(outputData);
    }
}