package interface_adapter.swap_views.conversation;

import interface_adapter.ViewManagerModel;
import interface_adapter.chat.ConversationViewModel;
import interface_adapter.chatbot.DialogflowViewModel;
import use_case.swap_views.chatbot.SwapToDialogflowOutputBoundary;

public class SwapToConversationPresenter implements SwapToConversationOutputBoundary {
    public final ViewManagerModel viewManagerModel;
    public final ConversationViewModel convoViewModel;

    public SwapToConversationPresenter(ViewManagerModel viewManagerModel, ConversationViewModel convoViewModel) {
        this.viewManagerModel = viewManagerModel;
        this.convoViewModel = convoViewModel;
    }

    @Override
    public void swapViews() {
        viewManagerModel.setActiveView(convoViewModel.getViewName());
        viewManagerModel.firePropertyChanged();
    }
}
