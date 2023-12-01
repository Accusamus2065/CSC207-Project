package interface_adapter.swap_views.chatbot;

import use_case.swap_views.chatbot.SwapToDialogflowInputBoundary;

public class SwapToDialogflowController {
    private final SwapToDialogflowInputBoundary swapToChatbotInteractor;

    public SwapToDialogflowController(SwapToDialogflowInputBoundary swapToChatbotInteractor) {
        this.swapToChatbotInteractor = swapToChatbotInteractor;
    }

    public void execute() {
        swapToChatbotInteractor.execute();
    }
}
