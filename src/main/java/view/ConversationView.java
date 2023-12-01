package view;

import app.ConvoUseCaseFactory;
import data_access.ConvoDAOImpl;
import data_access.DAOFacade;
import entity.chat.Message;
import interface_adapter.ViewManagerModel;
import interface_adapter.chat.ConversationController;
import interface_adapter.chat.ConversationState;
import interface_adapter.chat.ConversationViewModel;
import interface_adapter.login.LoginState;
import org.bson.Document;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.IOException;
import java.util.*;
import java.util.List;

public class ConversationView extends JPanel implements ActionListener, PropertyChangeListener {
    public final String viewName = "conversation view";
    private JFrame frame;
    private JPanel panel;
    private JButton logOutButton;
    private JTextArea chatArea;
    private JTextField messageField;
    private JButton sendButton;
    private String selfUsername;

    public static void main(String[] args) throws IOException {
        CardLayout cardLayout = new CardLayout();
        // The various View objects. Only one view is visible at a time.
        JPanel views = new JPanel(cardLayout);

        // This keeps track of and manages which view is currently showing.
        ViewManagerModel viewManagerModel = new ViewManagerModel();
        new ViewManager(views, cardLayout, viewManagerModel);

        ConversationView view = ConvoUseCaseFactory.create(viewManagerModel, new ConversationViewModel(), new DAOFacade(), "Marshal", "Harry");
        assert view != null;
        System.out.println(view.viewName);
        views.add(view, view.viewName);
        viewManagerModel.setActiveView(view.viewName);
        viewManagerModel.firePropertyChanged();
        view.show();
    }

    public ConversationView(ConversationViewModel viewModel, ConversationController controller, String selfUsername, String otherUsername) {
        this.selfUsername = selfUsername;
        frame = new JFrame();
        frame.setTitle("Chat Application");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setSize(800, 600); // Set your desired width and height
        frame.setLocationRelativeTo(null);

// Create and do settings for main panel
        panel = new JPanel();
        panel.setLayout(new FlowLayout(FlowLayout.CENTER, 20, 20)); // Adjust the gap between components
        panel.setBackground(Color.lightGray);
        panel.setPreferredSize(new Dimension(800, 500)); // Set your desired width and height
        frame.add(panel, BorderLayout.CENTER);

// Create the upper sub-panel
        JPanel upperPanel = new JPanel();
        upperPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 20, 10));
        upperPanel.setBackground(Color.lightGray);
        panel.add(upperPanel, BorderLayout.SOUTH);

// Create the button for logging out
        logOutButton = new JButton("Logout");
        logOutButton.setFont(new Font("Sans-serif", Font.PLAIN, 16));
        logOutButton.setFocusable(false);
        logOutButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Your action for log out
            }
        });
        logOutButton.setPreferredSize(new Dimension(100, 40)); // Set your desired width and height
        upperPanel.add(logOutButton);



// Create the chat sub-panel
        JPanel chatPanel = new JPanel();
        chatPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 100, 10));
        chatPanel.setBackground(Color.lightGray);
        panel.add(chatPanel, BorderLayout.CENTER);

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
        panel.add(messageFieldPanel, BorderLayout.SOUTH);



// Create the text field
        messageField = new JTextField(20);
        messageField.setFont(new Font("Sans-serif", Font.PLAIN, 16));
        messageField.setToolTipText("Enter your text");
        messageFieldPanel.add(messageField);

// Create the send button
        sendButton = new JButton("Send");
        sendButton.setFont(new Font("Sans-serif", Font.PLAIN, 16));
        sendButton.setFocusable(false);
        sendButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                controller.executeSave(selfUsername, otherUsername, messageField.getText());
            }
        });
        sendButton.setPreferredSize(new Dimension(100, 40)); // Set your desired width and height
        messageFieldPanel.add(sendButton);


        sendButton = new JButton("Refresh");
        sendButton.setFont(new Font("Sans-serif", Font.PLAIN, 16));
        sendButton.setFocusable(false);
        sendButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                chatArea.setText("");
                controller.executeRefresh(selfUsername, otherUsername);
                ConversationState state = viewModel.getState();
                List<Message> list = state.getMessages();
                for (Message message : list) {
                    if (message.getSender().equals( selfUsername)){
                        chatArea.append(message.getSender() + ": " + message.getContent() + message.getTimestamp() + "\n");
                    } else {
                        chatArea.append("\t\t\t\t\t" + message.getSender() + ": " + message.getContent() + message.getTimestamp() + "\n");
                    }
                }

            }
        });
        sendButton.setPreferredSize(new Dimension(100, 40)); // Set your desired width and height
        messageFieldPanel.add(sendButton);
    }

    public void show() {
        frame.setVisible(true);
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
    }


    @Override
    public void actionPerformed(ActionEvent e) {
    }
}
