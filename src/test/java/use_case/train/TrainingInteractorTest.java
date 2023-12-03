package use_case.train;

import data_access.InMemoryDialogFlowDataAccessObject;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

public class TrainingInteractorTest {
    private static final String INTENT = "TestIntent";
    private static final List<String> PHRASES = List.of("testPhrase1", "testPhrase2");
    private static final List<String> MESSAGES = List.of("testMessage1", "testMessage2");

    @Test
    public void intentTest() {
        InMemoryDialogFlowDataAccessObject dialogFlowDataAccessObject = new InMemoryDialogFlowDataAccessObject("Test");
        TrainingOutputBoundary trainingPresenter = new TrainingOutputBoundary() {
            @Override
            public void prepareSuccessView(TrainingOutputData messages) {
                assertEquals(INTENT, messages.getIntent());

                assertTrue(dialogFlowDataAccessObject.intents.containsKey(INTENT));
                List<Object> intents = dialogFlowDataAccessObject.intents.get(INTENT);
                assertEquals(PHRASES, intents.get(0));
                assertEquals(MESSAGES, intents.get(1));
            }

            @Override
            public void prepareFailView(String error) {
                fail("Test not expected to fail.");
            }
        };
        TrainingInputData inputData = new TrainingInputData(INTENT, PHRASES, MESSAGES);
        TrainingInteractor interactor = new TrainingInteractor(dialogFlowDataAccessObject, trainingPresenter);
        interactor.execute(inputData);
    }
}