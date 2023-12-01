package use_case.chatbot;


public class DialogflowInteractor implements DialogflowInputBoundary {
    final DialogflowOutputBoundary presenter;
    final DialogflowUserDataAccessInterface dao;

    public DialogflowInteractor(DialogflowUserDataAccessInterface convDao,
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