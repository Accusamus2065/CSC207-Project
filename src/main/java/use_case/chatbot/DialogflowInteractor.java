package use_case.chatbot;


import java.util.List;
<<<<<<< Updated upstream
=======
import java.util.Map;
>>>>>>> Stashed changes

public class DialogflowInteractor implements DialogflowInputBoundary {
    final DialogflowOutputBoundary presenter;
    final DialogflowUserDataAccessInterface dao;

    public DialogflowInteractor(DialogflowUserDataAccessInterface dao,
                                DialogflowOutputBoundary presenter) {
        this.dao = dao;
        this.presenter = presenter;
    }

    @Override
    public void execute(DialogflowInputData data) {
<<<<<<< Updated upstream
        List<Object> tuple = dao.detectIntent(data.getQuery());
        String response = (String) tuple.get(0);
        System.out.println(response);
        List<String> docNames = (List<String>) tuple.get(1);
        System.out.println(docNames);
        String username = (String) data.getUsername();
        DialogflowOutputData outputData = new DialogflowOutputData(
                response, username, docNames
        );
=======
        Map<String, List<String>> result = dao.detectIntent(data.getQuery());
        String response = result.keySet().iterator().next();
        List<String> docNames = result.get(response);
        DialogflowOutputData outputData = new DialogflowOutputData(response,data.getUsername(), docNames);
>>>>>>> Stashed changes
        presenter.prepareSuccessView(outputData);
    }
}