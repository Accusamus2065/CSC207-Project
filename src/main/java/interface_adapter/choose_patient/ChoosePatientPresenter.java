package interface_adapter.choose_patient;//package interface_adapter.login;



import interface_adapter.ViewManagerModel;

import interface_adapter.chat.ConversationViewModel;
import interface_adapter.update.doctor.DoctorUpdateViewModel;
import interface_adapter.welcome.WelcomeViewModel;
import use_case.choose_patient.ChoosePatientOutputBoundary;
import use_case.choose_patient.ChoosePatientOutputData;



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
//        ChoosePatientState choosePatientState = choosePatientViewModel.getState();
//        if (response.getUsecase().equals("logout")){
//            WelcomeState welcomeState = welcomeViewModel.getState();
//            // this.welcomeViewModel.setState(welcomeState);  // TODO WELCOMEVIEWMODEL NEEDS A SETSTATE METHOD
//            this.welcomeViewModel.firePropertyChanged();
//            this.viewManagerModel.setActiveView(welcomeViewModel.getViewName());
//            this.viewManagerModel.firePropertyChanged();
//        } else if (response.getUsecase().equals("choosePatient")){
//            ConversationState conversationState = conversationViewModel.getState();
//            conversationState.setMessages(null);
//            // conversationState.setUsername(response.getPatient()); // TODO CONVERSATION STATE NEEDS A SET USERNAME METHOD
//            this.conversationViewModel.setState(conversationState); // Also need to add who I am talking to
//            this.conversationViewModel.firePropertyChanged();
//            this.viewManagerModel.setActiveView(conversationViewModel.getViewName());
//            this.viewManagerModel.firePropertyChanged();
//        } else if (response.getUsecase().equals("update")) {
//            DoctorUpdateState doctorUpdateState = doctorUpdateViewModel.getState();
//            doctorUpdateState.setUsername(response.getUsername());
//            this.doctorUpdateViewModel.setState(doctorUpdateState);
//            this.doctorUpdateViewModel.firePropertyChanged();
//            this.viewManagerModel.setActiveView(doctorUpdateViewModel.getViewName());
//            this.viewManagerModel.firePropertyChanged();
//        }

    }

    @Override
    public void prepareFailView(String error) {
        ChoosePatientState choosePatientState = choosePatientViewModel.getState();
        choosePatientState.setError(error);
        choosePatientViewModel.firePropertyChanged();
    }


}

