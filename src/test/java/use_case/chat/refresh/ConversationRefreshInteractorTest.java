package use_case.chat.refresh;

import data_access.InMemoryConversationDataAccessObject;
import entity.chat.Message;
import org.junit.Test;
import use_case.chat.ConversationUserDataAccessInterface;

import java.util.Date;

import static org.junit.Assert.*;

public class ConversationRefreshInteractorTest {
    private static final String SENDER = "TestSender";
    private static final String RECEIVER = "TestReceiver";
    private static final String CONTENT = "TestMessage";
    private static final Date DATE = new Date();

    @Test
    public void refreshMessageTest() {
        ConversationUserDataAccessInterface dataAccessObject = new InMemoryConversationDataAccessObject();
        Message message = new Message(SENDER, RECEIVER, CONTENT);
        dataAccessObject.save(message);
        ConversationRefreshOutputBoundary presenter = messages -> {
            assertEquals(1, messages.getMessages().size());
            Message message1 = messages.getMessages().get(0);

            assertEquals(SENDER, message1.getSender());
            assertEquals(RECEIVER, message1.getReceiver());
            assertEquals(CONTENT, message1.getContent());
        };
        ConversationRefreshInputData inputData = new ConversationRefreshInputData(SENDER, RECEIVER);

        ConversationRefreshInteractor interactor = new ConversationRefreshInteractor(dataAccessObject, presenter);
        interactor.executeRefresh(inputData);
    }
}