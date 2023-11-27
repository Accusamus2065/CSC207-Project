package interface_adapter.chat;

import entity.chat.Message;
import interface_adapter.ViewManagerModel;
import use_case.chat.ConversationOutputBoundary;
import use_case.chat.ConversationOutputData;
import use_case.signup.SignupOutputData;

public class ConversationPresenter implements ConversationOutputBoundary {

    private final ConversationViewModel conversationViewModel;
    private ViewManagerModel viewManagerModel;

    public ConversationPresenter(ViewManagerModel viewManagerModel, ConversationViewModel conversationViewModel) {
        this.viewManagerModel = viewManagerModel;
        this.conversationViewModel = conversationViewModel;
    }

    @Override
    public void prepareSuccessView(ConversationOutputData messages) {
        ConversationState convoState = conversationViewModel.getState();
        convoState.setConversation(messages.getConversation());
        this.conversationViewModel.setState(convoState);
        conversationViewModel.firePropertyChanged();
        System.out.println(conversationViewModel.getState().getConversation());

        viewManagerModel.setActiveView(conversationViewModel.getViewName());
        viewManagerModel.firePropertyChanged();
    }

    @Override
    public void prepareFailView(String error) {

    }
}

