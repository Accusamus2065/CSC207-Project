package interface_adapter.swap_views.conversation;

import use_case.swap_views.chatbot.SwapToDialogflowInputBoundary;
import use_case.swap_views.conversation.SwapToConversationInputBoundary;

public class SwapToConversationController {
    private final SwapToConversationInputBoundary swapToConvoInteractor;

    public SwapToConversationController(SwapToConversationInputBoundary swapToConvoInteractor) {
        this.swapToConvoInteractor = swapToConvoInteractor;
    }

    public void execute(String sender, String receiver) {
        swapToConvoInteractor.execute(sender, receiver);
    }
}
