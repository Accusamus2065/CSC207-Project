package interface_adapter.login;

import interface_adapter.chat.ConversationState;
import interface_adapter.chat.DialogFlowViewModel;

import interface_adapter.ViewManagerModel;

import interface_adapter.choose_patient.ChoosePatientState;
import interface_adapter.choose_patient.ChoosePatientViewModel;
import use_case.login.LoginOutputBoundary;
import use_case.login.LoginOutputData;


public class LoginPresenter implements LoginOutputBoundary {

    private final LoginViewModel loginViewModel;
    private final DialogFlowViewModel dialogFlowViewModel;
    private final ChoosePatientViewModel choosePatientViewModel;
    private final ViewManagerModel viewManagerModel;
    public LoginPresenter(ViewManagerModel viewManagerModel,
                          DialogFlowViewModel dialogFlowViewModel,
                          ChoosePatientViewModel choosePatientViewModel,
                          LoginViewModel loginViewModel) {
        this.viewManagerModel = viewManagerModel;
        this.dialogFlowViewModel = dialogFlowViewModel;
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
            ConversationState conversationState = dialogFlowViewModel.getState();
            // conversationState.setUsername(response.getUsername());  // Conversation state needs method setUsername
            // conversationState.setConversation(null);  // TODO: Change this to actual conversation
            dialogFlowViewModel.setState(conversationState);
            dialogFlowViewModel.firePropertyChanged();

            viewManagerModel.setActiveView(dialogFlowViewModel.getViewName());
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

