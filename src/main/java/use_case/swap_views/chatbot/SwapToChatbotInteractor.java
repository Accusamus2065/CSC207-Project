package use_case.swap_views.chatbot;

public class SwapToChatbotInteractor implements SwapToChatbotInputBoundary {
    private final SwapToChatbotOutputBoundary swapToChatbotPresenter;

    public SwapToChatbotInteractor(SwapToChatbotOutputBoundary swapToChatbotPresenter) {
        this.swapToChatbotPresenter = swapToChatbotPresenter;
    }

    @Override
    public void execute() {
        swapToChatbotPresenter.swapViews();
    }
}
