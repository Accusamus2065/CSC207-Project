package use_case.chatbot;

import data_access.ConvoDAOImpl;
import data_access.DialogflowDAOImpl;
import entity.chat.Message;

import java.util.List;

public class DialogflowInteractor implements DialogflowInputBoundary {
    final DialogflowOutputBoundary presenter;
    final DialogflowDAOImpl dao;

    public DialogflowInteractor(DialogflowDAOImpl convDao,
                                  DialogflowOutputBoundary presenter) {
        this.dao = convDao;
        this.presenter = presenter;
    }

    @Override
    public void execute(DialogflowInputData data) {
        DialogflowOutputData outputData = new DialogflowOutputData(dao.detectIntent(data.getQuery()));
        presenter.prepareSuccessView(outputData);
    }
}