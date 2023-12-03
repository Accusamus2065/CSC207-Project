package interface_adapter.choose_patient;//package interface_adapter.login;



import interface_adapter.ViewManagerModel;

import interface_adapter.chat.refresh.ConversationRefreshViewModel;
import interface_adapter.chatbot.DialogflowViewModel;
import interface_adapter.update.doctor.DoctorUpdateViewModel;
import interface_adapter.welcome.WelcomeViewModel;
import use_case.choose_patient.ChoosePatientOutputBoundary;
import use_case.choose_patient.ChoosePatientOutputData;



public class ChoosePatientPresenter implements ChoosePatientOutputBoundary {

    private final ViewManagerModel viewManagerModel;
    private final ConversationRefreshViewModel conversationViewModel;
    private final WelcomeViewModel welcomeViewModel;
    private final DoctorUpdateViewModel doctorUpdateViewModel;
    private ChoosePatientViewModel choosePatientViewModel;

    public ChoosePatientPresenter(ViewManagerModel viewManagerModel,
                                  ConversationRefreshViewModel conversationViewModel,
                                  WelcomeViewModel welcomeViewModel,
                                  DoctorUpdateViewModel doctorUpdateViewModel,
                                  ChoosePatientViewModel choosePatientViewModel) {
        this.viewManagerModel = viewManagerModel;
        this.choosePatientViewModel = choosePatientViewModel;
        this.conversationViewModel = conversationViewModel;
        this.welcomeViewModel = welcomeViewModel;
        this.doctorUpdateViewModel = doctorUpdateViewModel;
    }

    @Override
    public void prepareSuccessView(ChoosePatientOutputData response) {
        ChoosePatientState choosePatientState = choosePatientViewModel.getState();
        DialogflowViewModel conversationState = conversationViewModel.getState();
        conversationState.setMessages(null);
        // conversationState.setUsername(response.getPatient()); // TODO CONVERSATION STATE NEEDS A SET USERNAME METHOD
        this..setState(conversationState); // Also need to add who I am talking to
        this.conversationViewModel.firePropertyChanged();
        this.viewManagerModel.setActiveView(conversationViewModel.getViewName());
        this.viewManagerModel.firePropertyChanged();

    }

    @Override
    public void prepareFailView(String error) {
        ChoosePatientState choosePatientState = choosePatientViewModel.getState();
        choosePatientState.setError(error);
        choosePatientViewModel.firePropertyChanged();
    }


}

