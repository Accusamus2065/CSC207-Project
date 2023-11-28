package interface_adapter.swap_views.welcome;

import interface_adapter.ViewManagerModel;
import interface_adapter.welcome.WelcomeViewModel;
import use_case.swap_views.welcome.SwapToWelcomeOutputBoundary;

public class SwapToWelcomePresenter implements SwapToWelcomeOutputBoundary {
    ViewManagerModel viewManagerModel;
    WelcomeViewModel welcomeViewModel;

    public SwapToWelcomePresenter(ViewManagerModel viewManagerModel, WelcomeViewModel welcomeViewModel) {
        this.viewManagerModel = viewManagerModel;
        this.welcomeViewModel = welcomeViewModel;
    }

    @Override
    public void execute() {
        viewManagerModel.setActiveView(welcomeViewModel.getViewName());
        viewManagerModel.firePropertyChanged();
    }
}
