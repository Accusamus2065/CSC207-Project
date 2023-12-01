package use_case.swap_views.chatbot;

public class SwapToDialogflowInteractor implements SwapToDialogflowInputBoundary {
    private final SwapToDialogflowOutputBoundary swapToChatbotPresenter;

    public SwapToDialogflowInteractor(SwapToDialogflowOutputBoundary swapToChatbotPresenter) {
        this.swapToChatbotPresenter = swapToChatbotPresenter;
    }

    @Override
    public void execute() {
        swapToChatbotPresenter.swapViews();
    }
}
