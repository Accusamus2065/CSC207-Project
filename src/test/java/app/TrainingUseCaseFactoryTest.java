package app;

import data_access.InMemoryDialogFlowDataAccessObject;
import interface_adapter.ViewManagerModel;
import interface_adapter.train.TrainingViewModel;
import org.junit.Test;
import use_case.train.TrainingUserDataAccessInterface;
import view.TrainingView;

import static org.junit.Assert.*;

public class TrainingUseCaseFactoryTest {
    @Test
    public void executeUseCase() {
        ViewManagerModel viewManagerModel = new ViewManagerModel();
        TrainingViewModel trainingViewModel = new TrainingViewModel();
        TrainingUserDataAccessInterface dataAccessObject = new InMemoryDialogFlowDataAccessObject("Test");

        TrainingView trainingView = TrainingUseCaseFactory.create(
                viewManagerModel,
                trainingViewModel,
                dataAccessObject);
        assertNotNull(trainingView);
        assertEquals(trainingViewModel.getViewName(), trainingView.viewName);
    }
}