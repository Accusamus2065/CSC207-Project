package view;

import interface_adapter.chatbot.DialogflowController;
import interface_adapter.chatbot.DialogflowState;
import interface_adapter.chatbot.DialogflowViewModel;

import interface_adapter.swap_views.chat.SwapToConversationController;
import interface_adapter.swap_views.login.SwapToLoginController;
import interface_adapter.swap_views.update.patient.SwapToPatientUpdateController;


import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

/**
 * The DialogflowView class represents the graphical user interface for interacting with the Dialogflow chatbot.
 * It includes components for sending and receiving messages, logging out, and modifying the user's profile.
 * The view dynamically displays buttons based on the available doctor names retrieved from Dialogflow responses.
 */
public class DialogflowView extends JPanel implements ActionListener, PropertyChangeListener {

    // The name of the view
    public final String viewName;

    // Components for the DialogflowView
    private JButton logOutButton;
    private JButton modifyButton;
    private JTextArea chatArea;
    private JTextField messageField;
    private JButton sendButton;
    private String username;
    private JPanel buttonPanel;
    private DialogflowController dialogflowController;
    private SwapToConversationController swapController;

    /**
     * Constructor for DialogflowView.
     *
     * @param viewModel            The view model providing the state for the view.
     * @param loginController      The controller for swapping to the login view.
     * @param dialogflowController The controller for interacting with Dialogflow.
     * @param swapController       The controller for swapping to the conversation view.
     */
    public DialogflowView(DialogflowViewModel viewModel,
                          SwapToLoginController loginController,
                          DialogflowController dialogflowController,
                          SwapToConversationController swapController,
                          SwapToPatientUpdateController updateController) {

        this.viewName = viewModel.getViewName();

        DialogflowState state = viewModel.getState();
        this.username = state.getUsername();

        this.dialogflowController = dialogflowController;

        this.swapController = swapController;

        viewModel.addPropertyChangeListener(this);

        // Main panel
        this.setLayout(new FlowLayout(FlowLayout.CENTER, 20, 20));
        this.setBackground(Color.lightGray);
        this.setPreferredSize(new Dimension(800, 500));

        // Upper sub-panel for buttons
        JPanel upperPanel = new JPanel();
        upperPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 472, 10));
        upperPanel.setBackground(Color.lightGray);
        this.add(upperPanel, BorderLayout.SOUTH);

        // Button for logging out
        logOutButton = new JButton("Logout");
        logOutButton.setFont(new Font("Sans-serif", Font.PLAIN, 16));
        logOutButton.setFocusable(false);
        logOutButton.addActionListener(e -> {
            loginController.execute();
            chatArea.setText("");
        });
        logOutButton.setPreferredSize(new Dimension(100, 40));
        upperPanel.add(logOutButton);

        // Button for modifying the profile (not implemented yet)
        modifyButton = new JButton("Modify Profile");
        modifyButton.setFont(new Font("Sans-serif", Font.PLAIN, 16));
        modifyButton.setFocusable(false);
        modifyButton.addActionListener(e -> {
            updateController.execute(state.getUsername());
            chatArea.setText("");
        });
        modifyButton.setPreferredSize(new Dimension(150, 40));
        upperPanel.add(modifyButton);

        // Chat sub-panel
        JPanel chatPanel = new JPanel();
        chatPanel.setLayout(new BorderLayout());
        chatPanel.setBackground(Color.lightGray);
        this.add(chatPanel, BorderLayout.CENTER);

        // Chat area where messages appear
        chatArea = new JTextArea();
        chatArea.setEditable(false);
        JScrollPane chatScrollPane = new JScrollPane(chatArea);
        chatScrollPane.setPreferredSize(new Dimension(600, 300));
        chatPanel.add(chatScrollPane, BorderLayout.CENTER);

        // Text field sub-panel
        JPanel messageAndButtonPanel = new JPanel();
        messageAndButtonPanel.setLayout(new BorderLayout());
        messageAndButtonPanel.setBackground(Color.lightGray);
        this.add(messageAndButtonPanel, BorderLayout.SOUTH);

        // Text field for entering messages
        messageField = new JTextField(20);
        messageField.setFont(new Font("Sans-serif", Font.PLAIN, 16));
        messageField.setToolTipText("Enter your text");
        messageAndButtonPanel.add(messageField, BorderLayout.CENTER);

        // Send button for sending messages
        sendButton = new JButton("Send");
        sendButton.setFont(new Font("Sans-serif", Font.PLAIN, 16));
        sendButton.setFocusable(false);
        sendButton.setPreferredSize(new Dimension(100, 40));
        sendButton.addActionListener(e -> {
            // Your action for sending message
            dialogflowController.execute(messageField.getText(), username);
            chatArea.append(messageField.getText() + "\n");
        });
        messageAndButtonPanel.add(sendButton, BorderLayout.EAST);

        // Sub-panel for dynamically generated buttons
        buttonPanel = new JPanel();
        buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.Y_AXIS));
        buttonPanel.setPreferredSize(new Dimension(100, 200));

        // Scrollable for the dynamically generated buttons
        JScrollPane buttonScrollPane = new JScrollPane(buttonPanel);
        buttonScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        buttonScrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);

        // Add the scrollable to the chatPanel
        chatPanel.add(buttonScrollPane, BorderLayout.EAST);

    }

    /**
     * Invoked when a property changes.
     *
     * @param evt The event fired when a property changes.
     */
    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        DialogflowState state = (DialogflowState) evt.getNewValue();
        if (state.getError() != null) {
            JOptionPane.showMessageDialog(this, state.getError());
        }
        username = state.getUsername();
        if (state.getResponse() != null) {
            chatArea.append(state.getResponse() + "\n");
        }
        System.out.println(state.getUsername());
        System.out.println("property changed dialogflowView");
        buttonPanel.removeAll();
        if (state.getDocNames() != null) {
            for (String docName : state.getDocNames()) {
                System.out.println(docName);
                JButton jButton = new JButton(docName);
                jButton.addActionListener(e -> {
                    // Your action for sending message
                    swapController.execute(username, docName);
                    chatArea.setText("");
                });
                buttonPanel.add(jButton);
            }
        }
        buttonPanel.revalidate();
        buttonPanel.repaint();
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