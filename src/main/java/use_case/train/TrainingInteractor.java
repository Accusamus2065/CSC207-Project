package use_case.train;


import java.util.List;


public class TrainingInteractor implements TrainingInputBoundary {
    final TrainingOutputBoundary presenter;
    final TrainingUserDataAccessInterface dao;

    // TODO: making DAO abstract
    public TrainingInteractor(TrainingUserDataAccessInterface dao,
                              TrainingOutputBoundary presenter) {
        this.dao = dao;
        this.presenter = presenter;
    }

    @Override
    public void execute(TrainingInputData data) {
        String intent = data.getIntent();
        List<String> phrases = data.getPhrases();
        List<String> messages = data.getMessages();
        try {
            dao.setIntentNEntities(intent, phrases, messages);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            presenter.prepareFailView("An error has occurred when adding the intent (intent possibly already exists)");
        }
        TrainingOutputData outputData = new TrainingOutputData(intent);
        presenter.prepareSuccessView(outputData);
    }
}