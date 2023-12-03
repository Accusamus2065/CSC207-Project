
package app;

import data_access.DialogflowDAOImpl;
import interface_adapter.ViewManagerModel;
import interface_adapter.login.LoginViewModel;
import interface_adapter.swap_views.login.SwapToLoginController;
import interface_adapter.swap_views.login.SwapToLoginPresenter;
import interface_adapter.train.TrainingController;
import interface_adapter.train.TrainingPresenter;
import interface_adapter.train.TrainingViewModel;
import use_case.swap_views.login.SwapToLoginInteractor;
import use_case.train.TrainingInputBoundary;
import use_case.train.TrainingInteractor;
import use_case.train.TrainingOutputBoundary;
import view.TrainingView;

public class TrainingUseCaseFactory {

    /* Prevent instantiation. */
    public TrainingUseCaseFactory() {
    }

    public static TrainingView create(ViewManagerModel viewManagerModel, TrainingViewModel trainingViewModel,
                                      DialogflowDAOImpl dao) {
        TrainingController controller = createTrainingUseCase(viewManagerModel, trainingViewModel, dao);
        SwapToLoginController swapController = createLoginUseCase(viewManagerModel, new LoginViewModel());
        return new TrainingView(trainingViewModel,swapController,controller);
    }

    // TODO: DialogflowDAOImpl needs to be changed to DAOFascade
    private static TrainingController createTrainingUseCase(ViewManagerModel viewManagerModel,
                                                            TrainingViewModel trainingViewModel,
                                                            DialogflowDAOImpl dao) {
        TrainingOutputBoundary presenter = new TrainingPresenter(viewManagerModel, trainingViewModel);

        TrainingInputBoundary interactor = new TrainingInteractor(dao, presenter);
        return new TrainingController(interactor);
    }

    private static SwapToLoginController createLoginUseCase(ViewManagerModel viewManagerModel, LoginViewModel loginViewModel) {
        SwapToLoginPresenter loginPresenter = new SwapToLoginPresenter(viewManagerModel, loginViewModel);
        SwapToLoginInteractor loginInteractor = new SwapToLoginInteractor(loginPresenter);
        return new SwapToLoginController(loginInteractor);


    }
}
