
package use_case.chatbot;

import data_access.InMemoryDialogFlowDataAccessObject;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

public class DialogflowInteractorTest {
    private static final String INTENT = "TestIntent";
    private static final String RESPONSE = "TestResponse";
    private static final List<String> DOCTORS = List.of("testDoctor1", "testDoctor2");
    private static final String USERNAME = "TestUsername";
    @Test
    public void DialogflowSuccessfulTest() {
        InMemoryDialogFlowDataAccessObject intentDAO = new InMemoryDialogFlowDataAccessObject(USERNAME);
        List<Object> objectList = List.of(RESPONSE, DOCTORS);
        intentDAO.responses.put(List.of(INTENT, USERNAME), objectList);

        DialogflowOutputBoundary dialogflowPresenter = new DialogflowOutputBoundary() {
            @Override
            public void prepareSuccessView(DialogflowOutputData messages) {
                assertEquals(DOCTORS, messages.getDocNames());
                assertEquals(RESPONSE, messages.getResponse());
                assertEquals(USERNAME, messages.getUsername());
            }

            @Override
            public void prepareFailView(String error) {
                fail("Test expected to pass.");
            }
        };
        DialogflowInteractor dialogflowInteractor = new DialogflowInteractor(intentDAO, dialogflowPresenter);

        DialogflowInputData inputData = new DialogflowInputData(INTENT, USERNAME);

        dialogflowInteractor.execute(inputData);
    }
}