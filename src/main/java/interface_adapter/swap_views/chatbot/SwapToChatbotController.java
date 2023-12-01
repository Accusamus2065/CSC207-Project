package interface_adapter.swap_views.chatbot;

import use_case.swap_views.chatbot.SwapToChatbotInputBoundary;

public class SwapToChatbotController {
    private final SwapToChatbotInputBoundary swapToChatbotInteractor;

    public SwapToChatbotController(SwapToChatbotInputBoundary swapToChatbotInteractor) {
        this.swapToChatbotInteractor = swapToChatbotInteractor;
    }

    public void execute() {
        swapToChatbotInteractor.execute();
    }
}
