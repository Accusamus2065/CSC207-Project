package view;

import interface_adapter.chatbot.DialogflowController;
import interface_adapter.chatbot.DialogflowState;
import interface_adapter.chatbot.DialogflowViewModel;
import interface_adapter.swap_views.login.SwapToLoginController;
import interface_adapter.train.TrainingController;
import interface_adapter.train.TrainingState;
import interface_adapter.train.TrainingViewModel;

import javax.swing.*;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TrainingView extends JPanel implements ActionListener, PropertyChangeListener {
    public final String viewName = "training view";

    private  JButton logOutButton;
    private  JTextField intentField;
    private  JTextField phraseField1;
    private  JTextField phraseField2;
    private  JTextField phraseField3;
    private  JTextField messageField1;
    private  JTextField messageField2;

    private  JButton sendButton;

    private TrainingController trainingController;


    public TrainingView(TrainingViewModel viewModel,
                        SwapToLoginController loginController,
                        TrainingController trainingController) {
        viewModel.addPropertyChangeListener(this);
        this.trainingController = trainingController;


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

        // Text field sub-panel
        JPanel messageAndButtonPanel = new JPanel();
        messageAndButtonPanel.setLayout(new BoxLayout(messageAndButtonPanel, BoxLayout.Y_AXIS));  // Use BoxLayout with Y_AXIS
        messageAndButtonPanel.setBackground(Color.lightGray);
        this.add(messageAndButtonPanel, BorderLayout.SOUTH);

// Text field for intent
        intentField = new JTextField(20);
        intentField.setFont(new Font("Sans-serif", Font.PLAIN, 16));
        intentField.setToolTipText("Enter your intent");
        messageAndButtonPanel.add(intentField);

// Text fields for training phrases
        phraseField1 = new JTextField(20);
        phraseField1.setFont(new Font("Sans-serif", Font.PLAIN, 16));
        phraseField1.setToolTipText("Enter your training phrase 1");
        messageAndButtonPanel.add(phraseField1);

        phraseField2 = new JTextField(20);
        phraseField2.setFont(new Font("Sans-serif", Font.PLAIN, 16));
        phraseField2.setToolTipText("Enter your training phrase 2");
        messageAndButtonPanel.add(phraseField2);

        phraseField3 = new JTextField(20);
        phraseField3.setFont(new Font("Sans-serif", Font.PLAIN, 16));
        phraseField3.setToolTipText("Enter your training phrase 3");
        messageAndButtonPanel.add(phraseField3);

// Text fields for response messages
        messageField1 = new JTextField(20);
        messageField1.setFont(new Font("Sans-serif", Font.PLAIN, 16));
        messageField1.setToolTipText("Enter your response message 1");
        messageAndButtonPanel.add(messageField1);

        messageField2 = new JTextField(20);
        messageField2.setFont(new Font("Sans-serif", Font.PLAIN, 16));
        messageField2.setToolTipText("Enter your response message 2");
        messageAndButtonPanel.add(messageField2);



// Text fields for training phrases
        addLabeledTextField(messageAndButtonPanel, "Training Phrase 1", phraseField1);
        addLabeledTextField(messageAndButtonPanel, "Training Phrase 2", phraseField2);
        addLabeledTextField(messageAndButtonPanel, "Training Phrase 3", phraseField3);

// Text fields for response messages
        addLabeledTextField(messageAndButtonPanel, "Response Message 1", messageField1);
        addLabeledTextField(messageAndButtonPanel, "Response Message 2", messageField2);

// Text field for intent
        addLabeledTextField(messageAndButtonPanel, "Intent", intentField);

// Send button
        sendButton = new JButton("Create");
        sendButton.setFont(new Font("Sans-serif", Font.PLAIN, 16));
        sendButton.setFocusable(false);
        sendButton.setPreferredSize(new Dimension(100, 40));
        sendButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Your action for sending message
                List<String> phrases = Arrays.asList(phraseField1.getText(), phraseField2.getText(), phraseField3.getText());
                List<String> messages = Arrays.asList(messageField1.getText(), messageField2.getText());

                trainingController.execute(intentField.getText(), phrases, messages);
            }
        });
        messageAndButtonPanel.add(sendButton);
    }
    // Helper method to add labeled text fields
    private void addLabeledTextField(JPanel panel, String labelText, JTextField textField) {
        JPanel subPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));

        JLabel label = new JLabel(labelText);
        label.setFont(new Font("Sans-serif", Font.PLAIN, 16));
        subPanel.add(label);

        subPanel.add(textField);

        panel.add(subPanel);
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        TrainingState state = (TrainingState) evt.getNewValue();
        if (state.getError() != null) {
            JOptionPane.showMessageDialog(this, state.getError());
        } else {
            JOptionPane.showMessageDialog(this, "Added intent " + state.getIntent() + " successfully");
        }
    }
    @Override
    public void actionPerformed(ActionEvent e) {

    }
}
