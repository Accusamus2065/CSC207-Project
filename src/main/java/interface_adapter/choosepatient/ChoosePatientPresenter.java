package interface_adapter.choosepatient;//package interface_adapter.login;



import interface_adapter.ViewManagerModel;

import interface_adapter.chat.ConversationState;
import interface_adapter.chat.ConversationViewModel;
import interface_adapter.welcome.WelcomeState;
import interface_adapter.welcome.WelcomeViewModel;
import use_case.login.LoginOutputBoundary;
import use_case.login.LoginOutputData;

import java.util.List;


public class ChoosePatientPresenter implements LoginOutputBoundary {

    private final ViewManagerModel viewManagerModel;
    private final ConversationViewModel conversationViewModel;
    private final WelcomeViewModel welcomeViewModel;
    private final ModifyViewModel modifyViewModel;
    private ChoosePatientViewModel choosePatientViewModel;
    private final ChoosePatientViewModel choosePatientViewModel;
    private final ConversationViewModel conversationViewModel;
    private ViewManagerModel viewManagerModel;

    public ChoosePatientPresenter(ViewManagerModel viewManagerModel,
                                  ConversationViewModel conversationViewModel,
                                  WelcomeViewModel welcomeViewModel,
                                  ModifyViewModel modifyViewModel,
                                  ChoosePatientViewModel choosePatientViewModel) {
        this.viewManagerModel = viewManagerModel;
        this.choosePatientViewModel = choosePatientViewModel;
        this.conversationViewModel = conversationViewModel;
        this.welcomeViewModel = welcomeViewModel;
        this.modifyViewModel = modifyViewModel;
    }

    @Override
    public void prepareSuccessView(LoginOutputData response) {
        //
        ChoosePatientState choosePatientState = choosePatientViewModel.getState();


        ConversationState conversationState = conversationViewModel.getState();
        conversationState.setConversation(null);
        WelcomeState welcomeState = welcomeViewModel.getState();

        ModifyState modifyState = modifyViewModel.getState();
        ConversationState conversationState = conversationViewModel.getState();
        conversationState.setUser(response.getUsername()); // TODO conversation state needs to be fixed, but not my file
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

