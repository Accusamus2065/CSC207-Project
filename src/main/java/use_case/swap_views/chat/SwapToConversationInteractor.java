package use_case.swap_views.chat;

public class SwapToConversationInteractor implements SwapToConversationInputBoundary {
    SwapToConversationOutputBoundary swapToConversationPresenter;

    public SwapToConversationInteractor(SwapToConversationOutputBoundary swapToConversationPresenter) {
        this.swapToConversationPresenter = swapToConversationPresenter;
    }

    @Override
    public void execute(String sender, String receiver) {
        swapToConversationPresenter.execute(sender, receiver);
    }
}
