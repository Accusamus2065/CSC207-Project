package interface_adapter.chat.refresh;

import entity.chat.Message;
import interface_adapter.ViewManagerModel;
import org.junit.Test;
import use_case.chat.refresh.ConversationRefreshOutputData;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.Assert.*;

public class ConversationRefreshPresenterTest {
    private static final String SENDER = "TestSender";
    private static final String RECEIVER = "TestReceiver";
    private static final String CONTENT = "TestMessage";
    private static final Date DATE = new Date();
    @Test
    public void successViewTest() {
        ViewManagerModel viewManagerModel = new ViewManagerModel();
        ConversationRefreshViewModel conversationViewModel = new ConversationRefreshViewModel();
        ConversationRefreshPresenter presenter = new ConversationRefreshPresenter(viewManagerModel, conversationViewModel);
        List<Message> messageList = new ArrayList<Message>();
        for (int i = 0; i < 10; i++) {
            Message message = new Message(
                    SENDER + i,
                    RECEIVER + i,
                    CONTENT + i,
                    DATE);
            messageList.add(message);
        }
        ConversationRefreshOutputData outputData = new ConversationRefreshOutputData(messageList);

        presenter.prepareSuccessView(outputData);
        assertEquals(conversationViewModel.getViewName(), viewManagerModel.getActiveView());
        assertEquals(messageList, conversationViewModel.getState().getMessages());
    }
}