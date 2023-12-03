package interface_adapter.chatbot;

import interface_adapter.ViewManagerModel;
import interface_adapter.login.LoginState;
import use_case.chatbot.DialogflowOutputBoundary;
import use_case.chatbot.DialogflowOutputData;


public class DialogflowPresenter implements DialogflowOutputBoundary {

    private final DialogflowViewModel dialogflowViewModel;
    private final ViewManagerModel viewManagerModel;

    public DialogflowPresenter(ViewManagerModel viewManagerModel, DialogflowViewModel dialogflowViewModel) {
        this.viewManagerModel = viewManagerModel;
        this.dialogflowViewModel = dialogflowViewModel;
    }

    @Override
    public void prepareSuccessView(DialogflowOutputData outputData) {
        DialogflowState dialogflowState = dialogflowViewModel.getState();
        dialogflowState.setError(null);
        dialogflowState.setResponse(outputData.getResponse());
        dialogflowState.setUsername(outputData.getUsername());
        dialogflowState.setDocNames(outputData.getDocNames());
        this.dialogflowViewModel.setState(dialogflowState);
        System.out.println(dialogflowState.getResponse());
        this.dialogflowViewModel.firePropertyChanged();

        this.viewManagerModel.setActiveView(dialogflowViewModel.getViewName());
        this.viewManagerModel.firePropertyChanged();
    }

    @Override
    public void prepareFailView(String error) {
        DialogflowState state = dialogflowViewModel.getState();
        state.setError(error);
        dialogflowViewModel.firePropertyChanged();
    }
}
