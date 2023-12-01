package interface_adapter.chat;

import interface_adapter.ViewManagerModel;
import use_case.chat.ConversationOutputBoundary;
import use_case.chat.ConversationOutputData;

public class ConversationPresenter implements ConversationOutputBoundary {

    private final DialogFlowViewModel dialogFlowViewModel;
    private ViewManagerModel viewManagerModel;

    public ConversationPresenter(ViewManagerModel viewManagerModel, DialogFlowViewModel dialogFlowViewModel) {
        this.viewManagerModel = viewManagerModel;
        this.dialogFlowViewModel = dialogFlowViewModel;
    }

    @Override
    public void prepareSuccessView(ConversationOutputData messages) {
        ConversationState convoState = dialogFlowViewModel.getState();
        convoState.setMessages(messages.getMessages());
        this.dialogFlowViewModel.setState(convoState);
        dialogFlowViewModel.firePropertyChanged();
        System.out.println(dialogFlowViewModel.getState().getMessages());

        viewManagerModel.setActiveView(dialogFlowViewModel.getViewName());
        viewManagerModel.firePropertyChanged();
    }

    @Override
    public void prepareFailView(String error) {

    }
}

