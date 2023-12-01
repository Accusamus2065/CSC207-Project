package interface_adapter.swap_views.chatbot;

import interface_adapter.ViewManagerModel;
import interface_adapter.chatbot.DialogflowViewModel;
import use_case.swap_views.chatbot.SwapToDialogflowOutputBoundary;

public class SwapToDialogflowPresenter implements SwapToDialogflowOutputBoundary {
    public final ViewManagerModel viewManagerModel;
    public final DialogflowViewModel dialogflowViewModel;

    public SwapToDialogflowPresenter(ViewManagerModel viewManagerModel, DialogflowViewModel dialogflowViewModel) {
        this.viewManagerModel = viewManagerModel;
        this.dialogflowViewModel = dialogflowViewModel;
    }

    @Override
    public void swapViews() {
        viewManagerModel.setActiveView(dialogflowViewModel.getViewName());
        viewManagerModel.firePropertyChanged();
    }
}
