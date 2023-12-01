package interface_adapter.chatbot;

import interface_adapter.ViewManagerModel;
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
        DialogflowState dialogflowState =  dialogflowViewModel.getState();
        dialogflowState.setResponse(outputData.getResponse());
        dialogflowState.setUsername(outputData.getUsername());
        this.dialogflowViewModel.setState(dialogflowState);
        System.out.println(dialogflowState.getResponse());
        this.dialogflowViewModel.firePropertyChanged();

        this.viewManagerModel.setActiveView(dialogflowViewModel.getViewName());
        this.viewManagerModel.firePropertyChanged();
    }

    @Override
    public void prepareFailView(String error) {

    }
}
