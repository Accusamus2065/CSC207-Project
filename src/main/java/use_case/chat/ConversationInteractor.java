package use_case.chat;

import entity.chat.Message;

import java.util.List;

public class ConversationInteractor implements ConversationInputBoundary {
    final ConversationOutputBoundary presenter;
    final ConversationUserDataAccessInterface dao;

    public ConversationInteractor(ConversationUserDataAccessInterface convDao,
                                  ConversationOutputBoundary presenter) {
        this.dao = convDao;
        this.presenter = presenter;
    }

    @Override
    public void executeSave(ConversationInputData data) {
        dao.save(data.getMessage());
    }

    @Override
    public void executeRefresh(ConversationInputData data) {
        List<Message> messages = dao.query(data.getMessage().getSender(), data.getMessage().getReceiver());
        ConversationOutputData outputData = new ConversationOutputData(messages);
        presenter.prepareSuccessView(outputData);
    }
}