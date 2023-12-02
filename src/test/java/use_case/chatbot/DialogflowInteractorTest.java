// TODO: dialogflow under edits
//package use_case.chatbot;
//
//import data_access.InMemoryUserDataAccessObject;
//import org.junit.Test;
//
//import static org.junit.Assert.*;
//
//public class DialogflowInteractorTest {
//    @Test
//    public void DialogflowSuccessfulTest() {
//        DialogflowUserDataAccessInterface userDAO = new InMemoryUserDataAccessObject();
//        DialogflowOutputBoundary dialogflowPresenter = new DialogflowOutputBoundary() {
//            @Override
//            public void prepareSuccessView(DialogflowOutputData messages) {
//                assertEquals("Hello, how can I help you?", messages.getResponse());
//            }
//
//            @Override
//            public void prepareFailView(String error) {
//                fail("Test expected to pass.");
//            }
//        };
//        DialogflowInteractor dialogflowInteractor = new DialogflowInteractor(userDAO, dialogflowPresenter);
//
//        DialogflowInputData inputData = new DialogflowInputData("Hello");
//        dialogflowInteractor.execute(inputData);
//    }
//}
