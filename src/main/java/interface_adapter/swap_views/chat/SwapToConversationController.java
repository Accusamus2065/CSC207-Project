package interface_adapter.swap_views.chat;

import use_case.swap_views.chat.SwapToConversationInputBoundary;

public class SwapToConversationController {
    SwapToConversationInputBoundary swapToConversationInteractor;

    public SwapToConversationController(SwapToConversationInputBoundary swapToConversationInteractor) {
        this.swapToConversationInteractor = swapToConversationInteractor;
    }

    public void execute(String sender, String receiver) {
        swapToConversationInteractor.execute(sender, receiver);
    }
}
