package view;

import app.DialogflowUseCaseFactory;
import data_access.DialogflowDAOImpl;
import interface_adapter.ViewManagerModel;
import interface_adapter.chatbot.DialogflowController;
import interface_adapter.chatbot.DialogflowState;
import interface_adapter.chatbot.DialogflowViewModel;
import interface_adapter.signup.SignupState;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.IOException;

public class DialogflowView extends JPanel implements ActionListener, PropertyChangeListener {
    public final String viewName = "dialogflow view";
    private JFrame frame;
    private JPanel panel;
    private JButton logOutButton;
    private JTextArea chatArea;
    private JTextField messageField;
    private JButton sendButton;
    private String username;
    private DialogflowViewModel viewModel;

    public static void main(String[] args) throws IOException {
        System.out.println("main");
        CardLayout cardLayout = new CardLayout();
        // The various View objects. Only one view is visible at a time.
        JPanel views = new JPanel(cardLayout);

        // This keeps track of and manages which view is currently showing.
        ViewManagerModel viewManagerModel = new ViewManagerModel();
        new ViewManager(views, cardLayout, viewManagerModel);

        DialogflowView view = DialogflowUseCaseFactory.create(viewManagerModel, new DialogflowViewModel(), new DialogflowDAOImpl(), "Marshal");
        System.out.println(view.viewName);
        views.add(view, view.viewName);
        viewManagerModel.setActiveView(view.viewName);
        viewManagerModel.firePropertyChanged();
        view.show();
    }

    public DialogflowView(DialogflowViewModel viewModel, DialogflowController controller, String username) {
        this.username = username;
        frame = new JFrame();
        frame.setTitle("Chat Application");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setSize(800, 600); // Set your desired width and height
        frame.setLocationRelativeTo(null);
        this.viewModel = viewModel;
        viewModel.addPropertyChangeListener(this);


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



        JPanel bottomPanel = new JPanel();
        bottomPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 10));
        bottomPanel.setBackground(Color.lightGray);
        panel.add(bottomPanel, BorderLayout.SOUTH);

    }

    public void show() {
        frame.setVisible(true);
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        System.out.println("reached property change");
        DialogflowState state = (DialogflowState) evt.getNewValue();
        chatArea.append(state.getResponse() + "\n");
        System.out.println(state.getResponse());
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }
}
