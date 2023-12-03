package use_case.train;

public class DialogflowInputData {
    final private String query;
    final private String username;

    public DialogflowInputData(String query, String username) {
        this.query = query;
        this.username = username;
    }

    public String getQuery() {
        return query;
    }
    public String getUsername() {
        return username;
    }
}
