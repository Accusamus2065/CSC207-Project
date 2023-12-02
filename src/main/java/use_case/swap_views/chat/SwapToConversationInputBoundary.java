package use_case.swap_views.chat;

public interface SwapToConversationInputBoundary {
    void execute(String sender, String receiver);
}
