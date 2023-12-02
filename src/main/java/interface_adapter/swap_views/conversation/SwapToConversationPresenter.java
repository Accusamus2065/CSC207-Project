package interface_adapter.swap_views.conversation;

import interface_adapter.ViewManagerModel;
import interface_adapter.chat.ConversationViewModel;
import interface_adapter.chatbot.DialogflowViewModel;
import use_case.swap_views.chatbot.SwapToDialogflowOutputBoundary;
import use_case.swap_views.conversation.SwapToConversationOutputBoundary;

public class SwapToConversationPresenter implements SwapToConversationOutputBoundary {
    public final ViewManagerModel viewManagerModel;
    public final ConversationViewModel convoViewModel;

    public SwapToConversationPresenter(ViewManagerModel viewManagerModel, ConversationViewModel convoViewModel) {
        this.viewManagerModel = viewManagerModel;
        this.convoViewModel = convoViewModel;
    }

    @Override
    public void swapViews(String sender, String receiver) {
        convoViewModel.getState().setSender(sender);
        convoViewModel.getState().setReceiver(receiver);
        viewManagerModel.setActiveView(convoViewModel.getViewName());
        viewManagerModel.firePropertyChanged();
    }
}
