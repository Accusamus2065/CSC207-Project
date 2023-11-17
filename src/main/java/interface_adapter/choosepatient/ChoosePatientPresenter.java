package interface_adapter.choosepatient;//package interface_adapter.login;



import interface_adapter.ViewManagerModel;

import interface_adapter.chat.ConversationState;
import interface_adapter.chat.ConversationViewModel;
import use_case.login.LoginOutputBoundary;
import use_case.login.LoginOutputData;

import java.util.List;


public class ChoosePatientPresenter implements LoginOutputBoundary {

    private final ChoosePatientViewModel choosePatientViewModel;
    private final ConversationViewModel conversationViewModel;
    private ViewManagerModel viewManagerModel;

    public ChoosePatientPresenter(ChoosePatientViewModel choosePatientViewModel,
                                  ConversationViewModel conversationViewModel,
                                  ViewManagerModel viewManagerModel) {
        this.choosePatientViewModel = choosePatientViewModel;
        this.conversationViewModel = conversationViewModel;
        this.viewManagerModel = viewManagerModel;
        this.loggedInViewModel = loggedInViewModel;
        this.loginViewModel = loginViewModel;
    }


    @Override
    public void prepareSuccessView(LoginOutputData response) {
        // On success, switch to the logged in view.

        ConversationState conversationState = conversationViewModel.getState();
        conversationState.setUser(response.getUsername()); // TODO conversation state needs to be fixed, but not my file
        this.conversationViewModel.setState(conversationState);
        this.conversationViewModel.firePropertyChanged();

        this.viewManagerModel.setActiveView(conversationViewModel.getViewName());
        this.viewManagerModel.firePropertyChanged();
    }

    @Override
    public void prepareFailView(String error) {
        ConversationState conversationState = conversationViewModel.getState();
        conversationState.setError(error); // TODO need to implement this too.
        conversationViewModel.firePropertyChanged();
    }


}

