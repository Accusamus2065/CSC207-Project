package interface_adapter.chat.refresh;
import use_case.chat.refresh.ConversationRefreshInputBoundary;
import use_case.chat.refresh.ConversationRefreshInputData;

import javax.xml.crypto.Data;
import java.util.Date;

public class ConversationRefreshController {

    final ConversationRefreshInputBoundary conversationRefreshUseCaseInteractor;
    public ConversationRefreshController(ConversationRefreshInputBoundary conversationRefreshUseCaseInteractor) {
        this.conversationRefreshUseCaseInteractor = conversationRefreshUseCaseInteractor;
    }

    public void executeRefresh(String sender, String receiver) {
        ConversationRefreshInputData data = new ConversationRefreshInputData(sender, receiver);
        conversationRefreshUseCaseInteractor.executeRefresh(data);
    }
}
