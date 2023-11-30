package use_case.chatbot;

public class DialogflowInputData {
    final private String query;

    public DialogflowInputData(String query) {
        this.query = query;
    }

    public String getQuery() {
        return query;
    }
}
