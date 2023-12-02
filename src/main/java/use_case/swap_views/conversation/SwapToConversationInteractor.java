package use_case.swap_views.conversation;

public class SwapToConversationInteractor implements SwapToConversationInputBoundary {
    private final SwapToConversationOutputBoundary swapToChatbotPresenter;

    public SwapToConversationInteractor(SwapToConversationOutputBoundary swapToChatbotPresenter) {
        this.swapToChatbotPresenter = swapToChatbotPresenter;
    }

    @Override
    public void execute(String sender, String receiver) {
        swapToChatbotPresenter.swapViews(sender, receiver);
    }
}
