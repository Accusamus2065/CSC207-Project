package use_case.swap_views.conversation;

public interface SwapToConversationInputBoundary {
    void execute(String sender, String receiver);
}
