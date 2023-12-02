package use_case.swap_views.chat;

public interface SwapToConversationOutputBoundary {
    void execute(String sender, String receiver);
}
