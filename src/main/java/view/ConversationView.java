package view;

import entity.chat.Message;

import interface_adapter.chat.refresh.ConversationRefreshController;
import interface_adapter.chat.refresh.ConversationRefreshState;
import interface_adapter.chat.refresh.ConversationRefreshViewModel;
import interface_adapter.chat.save.ConversationSaveController;
import interface_adapter.swap_views.chatbot.SwapToDialogflowController;
import interface_adapter.swap_views.list_of_patients.SwapToPatientListController;
import interface_adapter.swap_views.login.SwapToLoginController;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.List;

/**
 * The ConversationView class represents the graphical user interface for a conversation.
 * It includes components for displaying chat messages, sending messages, logging out, and refreshing the conversation.
 */
public class ConversationView extends JPanel implements ActionListener, PropertyChangeListener {

    // The name of the view
    public final String viewName;

    // Components for the ConversationView
    private JButton logOutButton;
    private JButton backButton;
    private JTextArea chatArea;
    private JTextField messageField;
    private JButton sendButton;

    private String sender;
    private String receiver;

    /**
     * Constructor for ConversationView.
     *
     * @param viewModel          The view model providing the state for the view.
     * @param swapController     The controller for swapping to the login view.
     * @param refreshController  The controller for refreshing the conversation.
     * @param saveController     The controller for saving a conversation message.
     */
    public ConversationView(ConversationRefreshViewModel viewModel,
                            SwapToLoginController swapController,
                            ConversationRefreshController refreshController,
                            ConversationSaveController saveController,
                            SwapToDialogflowController swapToDialogflowController,
                            SwapToPatientListController swapToPatientListController) {
        this.viewName = viewModel.getViewName();
        ConversationRefreshState state = viewModel.getState();
        viewModel.addPropertyChangeListener(this);

        // Set up the layout and appearance of the panel
        this.setLayout(new FlowLayout(FlowLayout.CENTER, 20, 20)); // Adjust the gap between components
        this.setBackground(Color.lightGray);
        this.setPreferredSize(new Dimension(800, 500)); // Set your desired width and height

        // Create the upper sub-panel
        JPanel upperPanel = new JPanel();
        upperPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 20, 10));
        upperPanel.setBackground(Color.lightGray);
        this.add(upperPanel, BorderLayout.SOUTH);

        // Create the button for logging out
        logOutButton = new JButton("Logout");
        logOutButton.setFont(new Font("Sans-serif", Font.PLAIN, 16));
        logOutButton.setFocusable(false);
        logOutButton.addActionListener(e -> swapController.execute());
        logOutButton.setPreferredSize(new Dimension(100, 40)); // Set your desired width and height
        upperPanel.add(logOutButton);

        // Create Back Button
        backButton = new JButton("Back");
        backButton.setFont(new Font("Sans-serif", Font.PLAIN, 16));
        backButton.setFocusable(false);
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ConversationRefreshState state = viewModel.getState();
                if(state.getIsDoctor()){
                    swapToPatientListController.execute();
                }else{
                    swapToDialogflowController.execute();
                }
            }
        });
        backButton.setPreferredSize(new Dimension(100, 40)); // Set your desired width and height
        upperPanel.add(backButton);

        // Create the chat sub-panel
        JPanel chatPanel = new JPanel();
        chatPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 100, 10));
        chatPanel.setBackground(Color.lightGray);
        this.add(chatPanel, BorderLayout.CENTER);

        // Create the chat area where messages appear
        chatArea = new JTextArea();
        chatArea.setEditable(false);
        chatArea.setPreferredSize(new Dimension(600, 300)); // Set your desired width and height
        JScrollPane chatScrollPane = new JScrollPane(chatArea);
        chatPanel.add(chatScrollPane, BorderLayout.CENTER);

        // Create the text field sub-panel
        JPanel messageFieldPanel = new JPanel();
        messageFieldPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 10));
        messageFieldPanel.setBackground(Color.lightGray);
        this.add(messageFieldPanel, BorderLayout.SOUTH);

        // Create the text field for entering messages
        messageField = new JTextField(20);
        messageField.setFont(new Font("Sans-serif", Font.PLAIN, 16));
        messageField.setToolTipText("Enter your text");
        messageFieldPanel.add(messageField);

        // Create the send button for sending messages
        sendButton = new JButton("Send");
        sendButton.setFont(new Font("Sans-serif", Font.PLAIN, 16));
        sendButton.setFocusable(false);
        sendButton.addActionListener(e -> saveController.executeSave(sender, receiver, messageField.getText()));
        sendButton.setPreferredSize(new Dimension(100, 40)); // Set your desired width and height
        messageFieldPanel.add(sendButton);

        // Create the refresh button for refreshing the conversation
        sendButton = new JButton("Refresh");
        sendButton.setFont(new Font("Sans-serif", Font.PLAIN, 16));
        sendButton.setFocusable(false);
        sendButton.addActionListener(e -> {
            chatArea.setText("");
            refreshController.executeRefresh(sender, receiver);
            List<Message> list = state.getMessages();
            for (Message message : list) {
                if (message.getSender().equals(sender)) {
                    chatArea.append(message.getSender() + ": " + message.getContent() + message.getTimestamp() + "\n");
                } else {
                    chatArea.append("\t\t\t\t\t" + message.getSender() + ": " + message.getContent() + message.getTimestamp() + "\n");
                }
            }
        });
        sendButton.setPreferredSize(new Dimension(100, 40)); // Set your desired width and height
        messageFieldPanel.add(sendButton);
    }

    /**
     * Invoked when a property changes.
     *
     * @param evt The event fired when a property changes.
     */
    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        ConversationRefreshState state = (ConversationRefreshState) evt.getNewValue();
        sender = state.getSender();
        receiver = state.getReceiver();
        // Handling of property changes can be implemented here
    }

    /**
     * Invoked when an action occurs.
     *
     * @param e The event representing the action.
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        // Handling of action events can be implemented here
    }
}