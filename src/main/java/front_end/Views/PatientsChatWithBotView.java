package front_end.Views;

import ViewModels.PatientsChatWithBotViewModel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PatientsChatWithBotView {
    private JFrame frame;
    private JPanel panel;
    private JButton logOutButton;
    private JLabel dialogFlowLabel;
    private JButton modifyButton;
    private JTextArea chatArea;
    private JTextField messageField;
    private JButton sendButton;

    public PatientsChatWithBotView() {

        // Create and do settings for frame
        frame = new JFrame();
        frame.setTitle(PatientsChatWithBotViewModel.TITLE_LABEL);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setSize(PatientsChatWithBotViewModel.FRAME_WIDTH_SIZE, PatientsChatWithBotViewModel.FRAME_HEIGHT_SIZE);
        frame.setLocationRelativeTo(null);

        // Create and do settings for main panel
        panel = new JPanel();
        panel.setLayout(new FlowLayout(FlowLayout.CENTER, 150, 20));
        panel.setBackground(Color.lightGray);
        panel.setPreferredSize(PatientsChatWithBotViewModel.PANEL_DIMENSION);
        frame.add(panel, BorderLayout.CENTER);

        // Create the upper sub-panel that will contain the button to log out, Dialogflow label, and modify button
        JPanel upperPanel = new JPanel();
        upperPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 150, 10));
        upperPanel.setBackground(Color.lightGray);
        panel.add(upperPanel, BorderLayout.SOUTH);

        // Create the button for logging out of the profile
        logOutButton = new JButton(PatientsChatWithBotViewModel.LOGOUT_BUTTON_LABEL);
        logOutButton.setFont(PatientsChatWithBotViewModel.BUTTON_FONT);
        logOutButton.setFocusable(false);
        logOutButton.addActionListener(
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) { //
                }});
        logOutButton.setPreferredSize(PatientsChatWithBotViewModel.BUTTON_DIMENSION);
        upperPanel.add(logOutButton);

        // Create and add label of DialogFlow to the upper panel
        dialogFlowLabel = new JLabel(PatientsChatWithBotViewModel.MAIN_LABEL);
        dialogFlowLabel.setFont(PatientsChatWithBotViewModel.MAIN_LABEL_FONT);
        upperPanel.add(dialogFlowLabel);

        // Create the button for modifying the profile
        modifyButton = new JButton(PatientsChatWithBotViewModel.MODIFY_BUTTON_LABEL);
        modifyButton.setFont(PatientsChatWithBotViewModel.BUTTON_FONT);
        modifyButton.setFocusable(false);
        modifyButton.addActionListener(
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) { //
                    }});
        modifyButton.setPreferredSize(PatientsChatWithBotViewModel.BUTTON_DIMENSION);
        upperPanel.add(modifyButton);

        // Create the chat sub-panel that contains chat area showing messages
        JPanel chatPanel = new JPanel();
        chatPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 100, 1));
        chatPanel.setBackground(Color.lightGray);
        panel.add(chatPanel, BorderLayout.CENTER);


        // Create the chat area where messages appear
        chatArea = new JTextArea();
        chatArea.setEditable(false);
        chatArea.setPreferredSize(PatientsChatWithBotViewModel.CHAT_AREA_DIMENSION);
        JScrollPane chatScrollPane = new JScrollPane(chatArea);
        chatPanel.add(chatScrollPane, BorderLayout.CENTER);

        // Create the text field sub-panel that contains input field and send button
        JPanel messageFieldPanel = new JPanel();
        messageFieldPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 1));
        messageFieldPanel.setBackground(Color.lightGray);
        panel.add(messageFieldPanel, BorderLayout.SOUTH);

        // Create the text field
        messageField = new JTextField(20);
        messageField.setFont(PatientsChatWithBotViewModel.MESSAGE_FIELD_FONT);
        messageField.setToolTipText("Enter your text");
        messageField.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ///
            }
        });
        messageFieldPanel.add(messageField);

        // Create the send button
        sendButton = new JButton(PatientsChatWithBotViewModel.SEND_BUTTON_LABEL);
        sendButton.setFont(PatientsChatWithBotViewModel.SEND_BUTTON_FONT);
        sendButton.setFocusable(false);
        sendButton.addActionListener(
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        chatArea.setText("Hello! How can I help you today? \n" +
                                "\n" +
                                "Link to Doctor 1 \n" +
                                "Link to Doctor 2");
                    }});
        sendButton.setPreferredSize(PatientsChatWithBotViewModel.SEND_BUTTON_DIMENSION);
        messageFieldPanel.add(sendButton);
    }
    public void show() {
        frame.setVisible(true);
    }
}

