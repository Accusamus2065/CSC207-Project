package view;

import interface_adapter.chatbot.DialogflowController;
import interface_adapter.chatbot.DialogflowState;
import interface_adapter.chatbot.DialogflowViewModel;

import interface_adapter.swap_views.chat.SwapToConversationController;
import interface_adapter.swap_views.login.SwapToLoginController;


import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class DialogflowView extends JPanel implements ActionListener, PropertyChangeListener {
    public final String viewName;
    private JButton logOutButton;
    private JButton modifyButton;
    private JTextArea chatArea;
    private JTextField messageField;
    private JButton sendButton;
    private String username;
    private JPanel buttonPanel;
    private DialogflowController dialogflowController;
    private SwapToConversationController swapController;

    public DialogflowView(DialogflowViewModel viewModel,
                          SwapToLoginController loginController,
                          DialogflowController dialogflowController,
                          SwapToConversationController swapController) {

        this.viewName = viewModel.getViewName();

        this.username = viewModel.getState().getUsername();

        this.dialogflowController = dialogflowController;

        this.swapController = swapController;

        viewModel.addPropertyChangeListener(this);

        // Main panel
        this.setLayout(new FlowLayout(FlowLayout.CENTER, 20, 20));
        this.setBackground(Color.lightGray);
        this.setPreferredSize(new Dimension(800, 500));

        // Upper sub-panel for logout button
        JPanel upperPanel = new JPanel();
        upperPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 472, 10));
        upperPanel.setBackground(Color.lightGray);
        this.add(upperPanel, BorderLayout.SOUTH);

        // Button for logging out
        logOutButton = new JButton("Logout");
        logOutButton.setFont(new Font("Sans-serif", Font.PLAIN, 16));
        logOutButton.setFocusable(false);
        logOutButton.addActionListener(e -> loginController.execute());
        logOutButton.setPreferredSize(new Dimension(100, 40));
        upperPanel.add(logOutButton);

        modifyButton = new JButton("Modify Profile");
        modifyButton.setFont(new Font("Sans-serif", Font.PLAIN, 16));
        modifyButton.setFocusable(false);
//        modifyButton.addActionListener(e -> loginController.execute());
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
        sendButton.addActionListener(e -> {
            // Your action for sending message
            dialogflowController.execute(messageField.getText(), username);
            chatArea.append(messageField.getText() + "\n");
        });
        messageAndButtonPanel.add(sendButton, BorderLayout.EAST);

        // Sub-panel for two buttons in the scrollable
        buttonPanel = new JPanel();
        buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.Y_AXIS));
        buttonPanel.setPreferredSize(new Dimension(100, 200));

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
                    chatArea.append(messageField.getText() + "\n");
                });
                buttonPanel.add(jButton);
            }
        }
        buttonPanel.revalidate();
        buttonPanel.repaint();
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }
}
