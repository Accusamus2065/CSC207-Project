package interface_adapter.swap_views.chat;

import interface_adapter.ViewManagerModel;
import interface_adapter.chat.refresh.ConversationRefreshState;
import interface_adapter.chat.refresh.ConversationRefreshViewModel;
import use_case.swap_views.chat.SwapToConversationOutputBoundary;

public class SwapToConversationPresenter implements SwapToConversationOutputBoundary {
    ViewManagerModel viewManagerModel;
    ConversationRefreshViewModel conversationRefreshViewModel;

    public SwapToConversationPresenter(ViewManagerModel viewManagerModel, ConversationRefreshViewModel conversationRefreshViewModel) {
        this.viewManagerModel = viewManagerModel;
        this.conversationRefreshViewModel = conversationRefreshViewModel;
    }

    @Override
    public void execute(String sender, String receiver) {
        ConversationRefreshState state = conversationRefreshViewModel.getState();
        state.setSender(sender);
        state.setReceiver(receiver);
        state.setIsDoctor(false);
        this.conversationRefreshViewModel.setState(state);
        conversationRefreshViewModel.firePropertyChanged();

        viewManagerModel.setActiveView(conversationRefreshViewModel.getViewName());
        viewManagerModel.firePropertyChanged();
    }
}
