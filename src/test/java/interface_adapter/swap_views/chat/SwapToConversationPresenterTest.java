package interface_adapter.swap_views.chat;

import interface_adapter.ViewManagerModel;
import interface_adapter.chat.refresh.ConversationRefreshState;
import interface_adapter.chat.refresh.ConversationRefreshViewModel;
import org.junit.Test;

import static org.junit.Assert.*;

public class SwapToConversationPresenterTest {
    private static final String SENDER = "TestSender";
    private static final String RECEIVER = "TestReceiver";

    @Test
    public void swapConversationTest() {
        ViewManagerModel viewManagerModel = new ViewManagerModel();
        ConversationRefreshViewModel conversationRefreshViewModel = new ConversationRefreshViewModel();
        SwapToConversationPresenter toConversationPresenter = new SwapToConversationPresenter(viewManagerModel, conversationRefreshViewModel);
        toConversationPresenter.execute(SENDER, RECEIVER);

        assertEquals(conversationRefreshViewModel.getViewName(), viewManagerModel.getActiveView());
        ConversationRefreshState state = conversationRefreshViewModel.getState();
        assertEquals(SENDER, state.getSender());
        assertEquals(RECEIVER, state.getReceiver());
    }
}