package interface_adapter.choose_patient;//package interface_adapter.login;


import interface_adapter.ViewManagerModel;

import interface_adapter.chat.refresh.ConversationRefreshState;
import interface_adapter.chat.refresh.ConversationRefreshViewModel;
import use_case.choose_patient.ChoosePatientOutputBoundary;
import use_case.choose_patient.ChoosePatientOutputData;


public class ChoosePatientPresenter implements ChoosePatientOutputBoundary {

    private final ViewManagerModel viewManagerModel;
    private final ConversationRefreshViewModel conversationRefreshViewModel;
    private final ChoosePatientViewModel choosePatientViewModel;

    public ChoosePatientPresenter(ViewManagerModel viewManagerModel,
                                  ConversationRefreshViewModel conversationRefreshViewModel,
                                  ChoosePatientViewModel choosePatientViewModel) {
        this.viewManagerModel = viewManagerModel;
        this.choosePatientViewModel = choosePatientViewModel;
        this.conversationRefreshViewModel = conversationRefreshViewModel;

    }

    @Override
    public void prepareSuccessView(ChoosePatientOutputData response) {
        ConversationRefreshState conversationRefreshState = conversationRefreshViewModel.getState();
        conversationRefreshState.setMessages(null);
        conversationRefreshState.setSender(response.getPatient());
        this.conversationRefreshViewModel.setState(conversationRefreshState); // Also need to add who I am talking to
        this.conversationRefreshViewModel.firePropertyChanged();
        this.viewManagerModel.setActiveView(conversationRefreshViewModel.getViewName());
        this.viewManagerModel.firePropertyChanged();

    }

    @Override
    public void prepareFailView(String error) {
        ChoosePatientState choosePatientState = choosePatientViewModel.getState();
        choosePatientState.setError(error);
        choosePatientViewModel.firePropertyChanged();
    }


}

