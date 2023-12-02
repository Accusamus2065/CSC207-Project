package use_case.chat.refresh;

import data_access.ConvoDAOImpl;
import entity.chat.Message;

import java.util.List;

public class ConversationRefreshInteractor implements ConversationRefreshInputBoundary {
    final ConversationRefreshOutputBoundary presenter;
    final ConvoDAOImpl dao;

    public ConversationRefreshInteractor(ConvoDAOImpl convDao,
                                         ConversationRefreshOutputBoundary presenter) {
        this.dao = convDao;
        this.presenter = presenter;
    }
    @Override
    public void executeRefresh(ConversationRefreshInputData data) {
        List<Message> messages = dao.query(data.getMessage().getSender(), data.getMessage().getReceiver());
        ConversationRefreshOutputData outputData = new ConversationRefreshOutputData(messages);
        presenter.prepareSuccessView(outputData);
    }

}
