package view;

import interface_adapter.chatbot.DialogflowController;
import interface_adapter.chatbot.DialogflowState;
import interface_adapter.chatbot.DialogflowViewModel;
import interface_adapter.swap_views.login.SwapToLoginController;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class DialogflowView extends JPanel implements ActionListener, PropertyChangeListener {
    public final String viewName = "dialogflow view";

    private final JButton logOutButton;
    private final JTextArea chatArea;
    private final JTextField messageField;
    private final JButton sendButton;
    private String username;

    public DialogflowView(DialogflowViewModel viewModel,
                          SwapToLoginController loginController,
                          DialogflowController controller) {

        this.username = viewModel.getState().getUsername();
        viewModel.addPropertyChangeListener(this);

        // Main panel
        this.setLayout(new FlowLayout(FlowLayout.CENTER, 20, 20));
        this.setBackground(Color.lightGray);
        this.setPreferredSize(new Dimension(800, 500));

        // Upper sub-panel for logout button
        JPanel upperPanel = new JPanel();
        upperPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 20, 10));
        upperPanel.setBackground(Color.lightGray);
        this.add(upperPanel, BorderLayout.SOUTH);

        // Button for logging out
        logOutButton = new JButton("Logout");
        logOutButton.setFont(new Font("Sans-serif", Font.PLAIN, 16));
        logOutButton.setFocusable(false);
        logOutButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                loginController.execute();
            }
        });
        logOutButton.setPreferredSize(new Dimension(100, 40));
        upperPanel.add(logOutButton);

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

        // Text field
        messageField = new JTextField(20);
        messageField.setFont(new Font("Sans-serif", Font.PLAIN, 16));
        messageField.setToolTipText("Enter your text");
        messageAndButtonPanel.add(messageField, BorderLayout.CENTER);

        // Send button
        sendButton = new JButton("Send");
        sendButton.setFont(new Font("Sans-serif", Font.PLAIN, 16));
        sendButton.setFocusable(false);
        sendButton.setPreferredSize(new Dimension(100, 40));
        sendButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Your action for sending message
                controller.execute(messageField.getText(), username);
                chatArea.append(messageField.getText() + "\n");
            }
        });
        messageAndButtonPanel.add(sendButton, BorderLayout.EAST);

        // Sub-panel for two buttons in the scrollable
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.Y_AXIS));

        // Two buttons for the scrollable
        JButton button1 = new JButton("Button 1");
        JButton button2 = new JButton("Button 2");

        // Add buttons to the panel
        buttonPanel.add(button1);
        buttonPanel.add(button2);

        // Scrollable for the buttons
        JScrollPane buttonScrollPane = new JScrollPane(buttonPanel);
        buttonScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        buttonScrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);

        // Add the scrollable to the chatPanel
        chatPanel.add(buttonScrollPane, BorderLayout.EAST);

    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        DialogflowState state = (DialogflowState) evt.getNewValue();
        username = state.getUsername();
        chatArea.append(state.getResponse() + "\n");
        System.out.println(state.getUsername());


    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }
}
