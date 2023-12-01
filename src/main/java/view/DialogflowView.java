package view;

import app.DialogflowUseCaseFactory;
import data_access.DAOFacade;
import interface_adapter.ViewManagerModel;
import interface_adapter.chatbot.DialogflowController;
import interface_adapter.chatbot.DialogflowState;
import interface_adapter.chatbot.DialogflowViewModel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.IOException;

public class DialogflowView extends JPanel implements ActionListener, PropertyChangeListener {
    public final String viewName = "dialogflow view";

    private final JFrame frame;
    private final JPanel panel;
    private final JButton logOutButton;
    private final JTextArea chatArea;
    private final  JTextField messageField;
    private final JButton sendButton;
    private final String username;
    private final DialogflowViewModel viewModel;

    public static void main(String[] args) throws IOException {
        System.out.println("main");
        CardLayout cardLayout = new CardLayout();
        // The various View objects. Only one view is visible at a time.
        JPanel views = new JPanel(cardLayout);

        // This keeps track of and manages which view is currently showing.
        ViewManagerModel viewManagerModel = new ViewManagerModel();
        new ViewManager(views, cardLayout, viewManagerModel);

        DialogflowView view = DialogflowUseCaseFactory.create(
                viewManagerModel,
                new DialogflowViewModel(),
                new DAOFacade(),
                "Marshal"
        );
        System.out.println(view.viewName);
        views.add(view, view.viewName);
        viewManagerModel.setActiveView(view.viewName);
        viewManagerModel.firePropertyChanged();
    }

    public DialogflowView(DialogflowViewModel viewModel, DialogflowController controller, String username) {

        this.username = username;
        frame = new JFrame();
        frame.setTitle("Chat Application");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setSize(800, 600);
        frame.setLocationRelativeTo(null);
        this.viewModel = viewModel;
        viewModel.addPropertyChangeListener(this);

        // Main panel
        panel = new JPanel();
        panel.setLayout(new FlowLayout(FlowLayout.CENTER, 20, 20));
        panel.setBackground(Color.lightGray);
        panel.setPreferredSize(new Dimension(800, 500));
        frame.add(panel, BorderLayout.CENTER);

        // Upper sub-panel for logout button
        JPanel upperPanel = new JPanel();
        upperPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 20, 10));
        upperPanel.setBackground(Color.lightGray);
        panel.add(upperPanel, BorderLayout.SOUTH);

        // Button for logging out
        logOutButton = new JButton("Logout");
        logOutButton.setFont(new Font("Sans-serif", Font.PLAIN, 16));
        logOutButton.setFocusable(false);
        logOutButton.addActionListener(e -> {
            // Your action for log out
        });
        logOutButton.setPreferredSize(new Dimension(100, 40));
        upperPanel.add(logOutButton);

        JPanel chatPanel = new JPanel();
        chatPanel.setLayout(new BorderLayout());
        chatPanel.setBackground(Color.lightGray);
        panel.add(chatPanel, BorderLayout.CENTER);

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
        panel.add(messageAndButtonPanel, BorderLayout.SOUTH);

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
                controller.execute(messageField.getText());
                chatArea.append(messageField.getText() + "\n");
            }
        });
        messageAndButtonPanel.add(sendButton, BorderLayout.EAST);

        JPanel messageFieldPanel = new JPanel();
        messageFieldPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 10));
        messageFieldPanel.setBackground(Color.lightGray);
        panel.add(messageFieldPanel, BorderLayout.SOUTH); // Add the sub-panel to the main panel (BorderLayout.SOUTH by default)

        // Sub-panel for two buttons in the scrollable
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.Y_AXIS));
// Create the text field
        messageField = new JTextField(20);
        messageField.setFont(new Font("Sans-serif", Font.PLAIN, 16));
        messageField.setToolTipText("Enter your text");
        messageFieldPanel.add(messageField);

// Create the send button
        sendButton = new JButton("Send");
        sendButton.setFont(new Font("Sans-serif", Font.PLAIN, 16));
        sendButton.setFocusable(false);
        sendButton.addActionListener(e -> {
            controller.execute(messageField.getText());
            chatArea.append(messageField.getText() + "\n");
        });
        sendButton.setPreferredSize(new Dimension(100, 40)); // Set your desired width and height
        messageFieldPanel.add(sendButton);

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

        frame.setVisible(true);
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        DialogflowState state = (DialogflowState) evt.getNewValue();
        username = state.getUsername();
        chatArea.append(state.getResponse() + "\n");
        System.out.println(state.getResponse());


    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }
}
