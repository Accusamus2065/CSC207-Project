package interface_adapter.choosepatient;//package interface_adapter.login;



import interface_adapter.ViewManagerModel;

import interface_adapter.chat.ConversationState;
import interface_adapter.chat.ConversationViewModel;
import interface_adapter.update.doctor.DoctorUpdateState;
import interface_adapter.welcome.WelcomeState;
import interface_adapter.welcome.WelcomeViewModel;
import use_case.choosepatient.ChoosePatientOutputData;
import use_case.login.LoginOutputBoundary;
import use_case.login.LoginOutputData;

import java.util.List;


public class ChoosePatientPresenter implements LoginOutputBoundary {

    private final ViewManagerModel viewManagerModel;
    private final ConversationViewModel conversationViewModel;
    private final WelcomeViewModel welcomeViewModel;
    private final DoctorUpdateState modifyViewModel;
    private ChoosePatientViewModel choosePatientViewModel;
    private final ChoosePatientViewModel choosePatientViewModel;
    private final ConversationViewModel conversationViewModel;
    private ViewManagerModel viewManagerModel;

    public ChoosePatientPresenter(ViewManagerModel viewManagerModel,
                                  ConversationViewModel conversationViewModel,
                                  WelcomeViewModel welcomeViewModel,
                                  DoctorUpdateState modifyViewModel,
                                  ChoosePatientViewModel choosePatientViewModel) {
        this.viewManagerModel = viewManagerModel;
        this.choosePatientViewModel = choosePatientViewModel;
        this.conversationViewModel = conversationViewModel;
        this.welcomeViewModel = welcomeViewModel;
        this.modifyViewModel = modifyViewModel;
    }

    @Override
    public void prepareSuccessView(ChoosePatientOutputData response) {
        ChoosePatientState choosePatientState = choosePatientViewModel.getState();
        if (response.getUsecase().equals("logout")){
            WelcomeState welcomeState = welcomeViewModel.getState();
            this.conversationViewModel.setState(conversationState);
            this.conversationViewModel.firePropertyChanged();
            this.viewManagerModel.setActiveView(conversationViewModel.getViewName());
            this.viewManagerModel.firePropertyChanged();
        } else if (response.getUsecase().equals("choosePatient")){
            ConversationState conversationState = conversationViewModel.getState();
            conversationState.setConversation(null);
            conversationState.setUser(response.getPatient()); // TODO conversation state needs to be fixed, but not my file
            this.conversationViewModel.setState(conversationState);
            this.conversationViewModel.firePropertyChanged();
            this.viewManagerModel.setActiveView(conversationViewModel.getViewName());
            this.viewManagerModel.firePropertyChanged();
        } else if (response.getUsecase("modify")) {
            DoctorUpdateState modifyState = modifyViewModel.getState();
            this.modifyViewModel.setState(modifyState);
            this.modifyViewModel.firePropertyChanged();
            this.viewManagerModel.setActiveView(modifyViewModel.getViewName());
            this.viewManagerModel.firePropertyChanged();
        }


    }

    @Override
    public void prepareFailView(String error) {
        ChoosePatientState choosePatientState = choosePatientViewModel.getState();
        choosePatientState.setError(error);
        choosePatientViewModel.firePropertyChanged();
    }


}

