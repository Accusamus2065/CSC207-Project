package interface_adapter.login;

import interface_adapter.chat.ConversationState;
import interface_adapter.chat.ConversationViewModel;

import interface_adapter.ViewManagerModel;

import use_case.login.LoginOutputBoundary;
import use_case.login.LoginOutputData;


public class LoginPresenter implements LoginOutputBoundary {

    private final LoginViewModel loginViewModel;
    private final ConversationViewModel conversationViewModel;
    private ViewManagerModel viewManagerModel;

    public LoginPresenter(ViewManagerModel viewManagerModel,
                          ConversationViewModel conversationViewModel,
                          LoginViewModel loginViewModel) {
        this.viewManagerModel = viewManagerModel;
        this.conversationViewModel = conversationViewModel;
        this.loginViewModel = loginViewModel;
    }

    @Override
    public void prepareSuccessView(LoginOutputData response) {
        // On success, switch to the logged in view.

        ConversationState conversationState = conversationViewModel.getState();
        conversationState.setConversation(null);  // TODO: Change this to actual conversation
        this.conversationViewModel.setState(conversationState);
        this.conversationViewModel.firePropertyChanged();

        this.viewManagerModel.setActiveView(conversationViewModel.getViewName());
        this.viewManagerModel.firePropertyChanged();
    }

    @Override
    public void prepareFailView(String error) {
        LoginState loginState = loginViewModel.getState();
        loginState.setUsernameError(error);
        loginViewModel.firePropertyChanged();
    }
}

