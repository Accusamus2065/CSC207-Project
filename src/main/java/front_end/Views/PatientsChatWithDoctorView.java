package front_end.Views;

import ViewModels.PatientsChatWithDoctorViewModel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PatientsChatWithDoctorView {
    private JFrame frame;
    private JPanel panel;
    private JButton backButton;
    private JLabel dialogFlowLabel;
    private JButton modifyButton;
    private JTextArea chatArea;
    private JTextField messageField;
    private JButton sendButton;

    public PatientsChatWithDoctorView() {

        // Create and do settings for frame
        frame = new JFrame();
        frame.setTitle(PatientsChatWithDoctorViewModel.TITLE_LABEL);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setSize(PatientsChatWithDoctorViewModel.FRAME_WIDTH_SIZE, PatientsChatWithDoctorViewModel.FRAME_HEIGHT_SIZE);
        frame.setLocationRelativeTo(null);

        // Create and do settings for main panel
        panel = new JPanel();
        panel.setLayout(new FlowLayout(FlowLayout.CENTER, 150, 25));
        panel.setBackground(Color.lightGray);
        panel.setPreferredSize(PatientsChatWithDoctorViewModel.PANEL_DIMENSION);
        frame.add(panel, BorderLayout.SOUTH);

        // Create the upper sub-panel that will contain the button for back, Dialogflow label, and modify button
        JPanel upperPanel = new JPanel();
        upperPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 150, 20));
        upperPanel.setBackground(Color.lightGray);
        panel.add(upperPanel, BorderLayout.SOUTH);

        // Create the button for going back
        backButton = new JButton(PatientsChatWithDoctorViewModel.BACK_BUTTON_LABEL);
        backButton.setFont(PatientsChatWithDoctorViewModel.BUTTON_FONT);
        backButton.setFocusable(false);
        backButton.addActionListener(
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) { //
                    }});
        backButton.setPreferredSize(PatientsChatWithDoctorViewModel.BUTTON_DIMENSION);
        upperPanel.add(backButton);

        // Create and add label of DialogFlow to the upper panel
        dialogFlowLabel = new JLabel("Doctor 1");
        dialogFlowLabel.setFont(new Font("Sans-serif", Font.BOLD, 40));
        upperPanel.add(dialogFlowLabel);

        // Create the button for going back
        modifyButton = new JButton(PatientsChatWithDoctorViewModel.MODIFY_BUTTON_LABEL);
        modifyButton.setFont(PatientsChatWithDoctorViewModel.BUTTON_FONT);
        modifyButton.setFocusable(false);
        modifyButton.addActionListener(
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) { //
                    }});
        modifyButton.setPreferredSize(PatientsChatWithDoctorViewModel.BUTTON_DIMENSION);
        upperPanel.add(modifyButton);

        // Create the chat sub-panel that contains chat area showing messages
        JPanel chatPanel = new JPanel();
        chatPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 150, 1));
        chatPanel.setBackground(Color.lightGray);
        panel.add(chatPanel, BorderLayout.SOUTH);

        // Create the chat area where messages appear
        chatArea = new JTextArea();
        chatArea.setEditable(false);
        chatArea.setPreferredSize(PatientsChatWithDoctorViewModel.CHAT_AREA_DIMENSION);
        JScrollPane chatScrollPane = new JScrollPane(chatArea);
        chatPanel.add(chatScrollPane, BorderLayout.CENTER);

        // Create the text field sub-panel that contains input field and send button
        JPanel messageFieldPanel = new JPanel();
        messageFieldPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 1));
        messageFieldPanel.setBackground(Color.lightGray);
        panel.add(messageFieldPanel, BorderLayout.SOUTH);

        // Create the text field
        messageField = new JTextField(20);
        messageField.setFont(PatientsChatWithDoctorViewModel.MESSAGE_FIELD_FONT);
        messageField.setToolTipText("Enter your text");
        messageField.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ///
            }
        });
        messageFieldPanel.add(messageField);

        // Create the send button
        sendButton = new JButton(PatientsChatWithDoctorViewModel.SEND_BUTTON_LABEL);
        sendButton.setFont(PatientsChatWithDoctorViewModel.SEND_BUTTON_FONT);
        sendButton.setFocusable(false);
        sendButton.addActionListener(
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) { //
                    }});
        sendButton.setPreferredSize(PatientsChatWithDoctorViewModel.SEND_BUTTON_DIMENSION);
        messageFieldPanel.add(sendButton);
    }
    public void show() {
        frame.setVisible(true);
    }
}
