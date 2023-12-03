package interface_adapter.chat.refresh;

import org.junit.Test;
import use_case.chat.refresh.ConversationRefreshInputBoundary;

import static org.junit.Assert.*;

public class ConversationRefreshControllerTest {
    private static final String SENDER = "TestSender";
    private static final String RECEIVER = "TestReceiver";
    @Test
    public void executeTest() {
        ConversationRefreshInputBoundary interactor = data -> {
            assertEquals(SENDER, data.getMessage().getSender());
            assertEquals(RECEIVER, data.getMessage().getReceiver());
        };
        ConversationRefreshController controller = new ConversationRefreshController(interactor);
        controller.executeRefresh(SENDER, RECEIVER);
    }
}