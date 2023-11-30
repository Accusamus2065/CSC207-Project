package interface_adapter.login;

import interface_adapter.chat.ConversationState;
import interface_adapter.chat.ConversationViewModel;

import interface_adapter.ViewManagerModel;

import interface_adapter.choosepatient.ChoosePatientState;
import interface_adapter.choosepatient.ChoosePatientViewModel;
import use_case.login.LoginOutputBoundary;
import use_case.login.LoginOutputData;


public class LoginPresenter implements LoginOutputBoundary {

    private final LoginViewModel loginViewModel;
    private final ConversationViewModel conversationViewModel;
    private final ChoosePatientViewModel choosePatientViewModel;
    private final ViewManagerModel viewManagerModel;

    public LoginPresenter(ViewManagerModel viewManagerModel,
                          ConversationViewModel conversationViewModel,
                          ChoosePatientViewModel choosePatientViewModel,
                          LoginViewModel loginViewModel) {
        this.viewManagerModel = viewManagerModel;
        this.conversationViewModel = conversationViewModel;
        this.loginViewModel = loginViewModel;
        this.choosePatientViewModel = choosePatientViewModel;
    }

    @Override
    public void prepareSuccessView(LoginOutputData response) {
        // On success, switch to the choosepatient or conversation in view.

        LoginState loginState = loginViewModel.getState();

        if (loginState.isDoctor()){
            ChoosePatientState choosePatientState = choosePatientViewModel.getState();
            choosePatientState.setUsername(response.getUsername());
            this.choosePatientViewModel.setState(choosePatientState);

            choosePatientViewModel.firePropertyChanged();

            viewManagerModel.setActiveView(choosePatientViewModel.getViewName());
            viewManagerModel.firePropertyChanged();
        } else {
            ConversationState conversationState = conversationViewModel.getState();
            // conversationState.setUsername(response.getUsername());  // Conversation state needs method setUsername
            // conversationState.setConversation(null);  // TODO: Change this to actual conversation
            conversationViewModel.setState(conversationState);
            conversationViewModel.firePropertyChanged();

            viewManagerModel.setActiveView(conversationViewModel.getViewName());
            viewManagerModel.firePropertyChanged();
        }
    }

    @Override
    public void prepareFailView(String error) {
        LoginState loginState = loginViewModel.getState();
        loginState.setError(error);
        loginViewModel.firePropertyChanged();
    }
}

