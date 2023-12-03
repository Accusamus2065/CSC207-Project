package interface_adapter.chat.save;

import org.junit.Test;
import use_case.chat.save.ConversationSaveInputBoundary;

import static org.junit.Assert.*;

public class ConversationSaveControllerTest {
    private static final String SENDER = "TestSender";
    private static final String RECEIVER = "TestReceiver";
    private static final String CONTENT = "TestMessage";
    @Test
    public void executeControllerTest() {
        ConversationSaveInputBoundary interactor = data -> {
            assertEquals(SENDER, data.getMessage().getSender());
            assertEquals(RECEIVER, data.getMessage().getReceiver());
            assertEquals(CONTENT, data.getMessage().getContent());
        };
        ConversationSaveController controller = new ConversationSaveController(interactor);
        controller.executeSave(SENDER, RECEIVER, CONTENT);
    }
}