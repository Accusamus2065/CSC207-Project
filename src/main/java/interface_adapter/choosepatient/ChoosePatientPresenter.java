package interface_adapter.choosepatient;//package interface_adapter.login;



import interface_adapter.ViewManagerModel;

import interface_adapter.chat.ConversationState;
import interface_adapter.chat.ConversationViewModel;
import interface_adapter.update.doctor.DoctorUpdateState;
import interface_adapter.update.doctor.DoctorUpdateViewModel;
import interface_adapter.welcome.WelcomeState;
import interface_adapter.welcome.WelcomeViewModel;
import use_case.choosepatient.ChoosePatientInputBoundary;
import use_case.choosepatient.ChoosePatientOutputBoundary;
import use_case.choosepatient.ChoosePatientOutputData;
import use_case.login.LoginOutputBoundary;
import use_case.login.LoginOutputData;

import java.util.List;


public class ChoosePatientPresenter implements ChoosePatientOutputBoundary {

    private final ViewManagerModel viewManagerModel;
    private final ConversationViewModel conversationViewModel;
    private final WelcomeViewModel welcomeViewModel;
    private final DoctorUpdateViewModel doctorUpdateViewModel;
    private ChoosePatientViewModel choosePatientViewModel;

    public ChoosePatientPresenter(ViewManagerModel viewManagerModel,
                                  ConversationViewModel conversationViewModel,
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
        if (response.getUsecase().equals("logout")){
            WelcomeState welcomeState = welcomeViewModel.getState();
            this.welcomeViewModel.setState(welcomeState);  //Welcome Viewmodel does not have a setstate function yet
            this.welcomeViewModel.firePropertyChanged();
            this.viewManagerModel.setActiveView(welcomeViewModel.getViewName());
            this.viewManagerModel.firePropertyChanged();
        } else if (response.getUsecase().equals("choosePatient")){
            ConversationState conversationState = conversationViewModel.getState();
            conversationState.setConversation(null);
            conversationState.setUser(response.getPatient()); // TODO conversation state needs to be fixed, but not my file
            this.conversationViewModel.setState(conversationState);
            this.conversationViewModel.firePropertyChanged();
            this.viewManagerModel.setActiveView(conversationViewModel.getViewName());
            this.viewManagerModel.firePropertyChanged();
        } else if (response.getUsecase().equals("update")) {
            DoctorUpdateState doctorUpdateState = doctorUpdateViewModel.getState();
            this.doctorUpdateViewModel.setState(doctorUpdateState);
            this.doctorUpdateViewModel.firePropertyChanged();
            this.viewManagerModel.setActiveView(doctorUpdateViewModel.getViewName());
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

