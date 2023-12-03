package use_case.swap_views.welcome;

public class SwapToWelcomeData {
    private final String viewName;

    public SwapToWelcomeData(String viewName) {
        this.viewName = viewName;
    }

    public String getViewName() {
        return viewName;
    }
}
