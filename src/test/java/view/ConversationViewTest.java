package view;

import interface_adapter.chat.refresh.ConversationRefreshController;
import interface_adapter.chat.refresh.ConversationRefreshViewModel;
import interface_adapter.chat.save.ConversationSaveController;
import interface_adapter.swap_views.chatbot.SwapToDialogflowController;
import interface_adapter.swap_views.list_of_patients.SwapToPatientListController;
import interface_adapter.swap_views.login.SwapToLoginController;
import org.junit.Before;
import org.junit.Test;
import use_case.swap_views.list_of_patients.SwapToPatientListInteractor;

import javax.swing.*;
import java.awt.*;

import static org.junit.Assert.*;

public class ConversationViewTest {

    private ConversationView conversationView;

    @Before
    public void setUp() {
        ConversationRefreshViewModel viewModel = new ConversationRefreshViewModel();
        SwapToLoginController swapController = new SwapToLoginController(() -> {
            assert true;
        });
        ConversationRefreshController refreshController = new ConversationRefreshController(data -> {
            assert true;
        });

        ConversationSaveController saveController = new ConversationSaveController(data -> {
            assert true;
        });

        SwapToDialogflowController swapToDialogflowController = new SwapToDialogflowController(() -> {
            assert true;
        });

        SwapToPatientListController swapToPatientListController = new SwapToPatientListController(new SwapToPatientListInteractor(() -> {
            assert true;
        }));
        conversationView = new ConversationView(viewModel, swapController, refreshController, saveController, swapToDialogflowController, swapToPatientListController);
    }

    @Test
    public void testComponentsExist() {
        // Checking that

        // Upper panel components
        assertEquals(3, conversationView.getComponentCount());
        assertTrue(conversationView.getComponent(0) instanceof JPanel); // Upper panel
        assertTrue(conversationView.getComponent(1) instanceof JPanel); // Chat panel
        assertTrue(conversationView.getComponent(2) instanceof JPanel);

        // Verify logOutButton
        JPanel upperPanel = (JPanel) conversationView.getComponent(0);
        Component[] upperPanelComponents = upperPanel.getComponents();
        JButton logOutButton = null;
        for (Component component : upperPanelComponents) {
            if (component instanceof JButton && "Logout".equals(((JButton) component).getText())) {
                logOutButton = (JButton) component;
                break;
            }
        }
        assertNotNull(logOutButton);

        // Verify chatArea
        JPanel chatPanel = (JPanel) conversationView.getComponent(1);
        Component[] chatPanelComponents = chatPanel.getComponents();
        JTextArea chatArea = null;
        for (Component component : chatPanelComponents) {
            if (component instanceof JScrollPane) {
                JScrollPane scrollPane = (JScrollPane) component;
                chatArea = (JTextArea) scrollPane.getViewport().getView();
                break;
            }
        }
        assertNotNull(chatArea);

        // Verify messageField and sendButton
        assertTrue(conversationView.getComponent(2) instanceof JPanel); // Message field panel
        JPanel messageFieldPanel = (JPanel) conversationView.getComponent(2);
        Component[] messageFieldPanelComponents = messageFieldPanel.getComponents();
        JTextField messageField = null;
        JButton sendButton = null;
        for (Component component : messageFieldPanelComponents) {
            if (component instanceof JTextField) {
                messageField = (JTextField) component;
            } else if (component instanceof JButton && "Send".equals(((JButton) component).getText())) {
                sendButton = (JButton) component;
            }
        }
        assertNotNull(messageField);
        assertNotNull(sendButton);
    }

}
