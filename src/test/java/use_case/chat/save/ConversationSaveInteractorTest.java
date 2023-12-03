package use_case.chat.save;

import data_access.InMemoryConversationDataAccessObject;
import entity.chat.Message;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

public class ConversationSaveInteractorTest {
    private static final String SENDER = "TestSender";
    private static final String RECEIVER = "TestReceiver";
    private static final String CONTENT = "TestMessage";

    @Test
    public void saveMessageTest() {
        InMemoryConversationDataAccessObject dataAccessObject = new InMemoryConversationDataAccessObject();
        ConversationSaveInputData inputData = new ConversationSaveInputData(SENDER, RECEIVER, CONTENT);
        ConversationSaveInteractor interactor = new ConversationSaveInteractor(dataAccessObject);
        interactor.executeSave(inputData);

        List<Message> messageList = dataAccessObject.messages.get(List.of(SENDER, RECEIVER));
        assertNotNull(messageList);
        assertEquals(1, messageList.size());
        Message message = messageList.get(0);
        assertEquals(SENDER, message.getSender());
        assertEquals(RECEIVER, message.getReceiver());
        assertEquals(CONTENT, message.getContent());
    }
}