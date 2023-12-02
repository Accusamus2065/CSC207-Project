package interface_adapter.chat.refresh;

import interface_adapter.ViewManagerModel;
import use_case.chat.refresh.ConversationRefreshOutputBoundary;
import use_case.chat.refresh.ConversationRefreshOutputData;

public class ConversationRefreshPresenter implements ConversationRefreshOutputBoundary {
    private final ConversationRefreshViewModel conversationRefreshViewModel;
    private ViewManagerModel viewManagerModel;

    public ConversationRefreshPresenter(ViewManagerModel viewManagerModel, ConversationRefreshViewModel conversationRefreshViewModel) {
        this.viewManagerModel = viewManagerModel;
        this.conversationRefreshViewModel = conversationRefreshViewModel;
    }

    @Override
    public void prepareSuccessView(ConversationRefreshOutputData messages) {
        ConversationRefreshState convoRefreshState = conversationRefreshViewModel.getState();
        convoRefreshState.setMessages(messages.getMessages());
        this.conversationRefreshViewModel.setState(convoRefreshState);
        conversationRefreshViewModel.firePropertyChanged();
        System.out.println(conversationRefreshViewModel.getState().getMessages());

        viewManagerModel.setActiveView(conversationRefreshViewModel.getViewName());
        viewManagerModel.firePropertyChanged();
    }

    @Override
    public void prepareFailView(String error) {
    }
}
