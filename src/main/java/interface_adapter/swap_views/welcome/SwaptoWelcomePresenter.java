package interface_adapter.swap_views.welcome;

import interface_adapter.ViewManagerModel;
import interface_adapter.welcome.WelcomeViewModel;
import use_case.swap_views.SwaptoWelcomeOutputBoundary;

public class SwaptoWelcomePresenter implements SwaptoWelcomeOutputBoundary {
    ViewManagerModel viewManagerModel;
    WelcomeViewModel welcomeViewModel;

    public SwaptoWelcomePresenter(ViewManagerModel viewManagerModel, WelcomeViewModel welcomeViewModel) {
        this.viewManagerModel = viewManagerModel;
        this.welcomeViewModel = welcomeViewModel;
    }

    @Override
    public void execute() {
        viewManagerModel.setActiveView("welcome");
        viewManagerModel.firePropertyChanged();
    }
}
