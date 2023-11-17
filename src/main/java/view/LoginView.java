package view;


import front_end.ViewModels.LoginViewModel;
import interface_adapter.login.LoginController;
import interface_adapter.login.LoginState;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class LoginView implements ActionListener, PropertyChangeListener {
    private final JFrame frame;
    private JPanel panel;
    private JTextField usernameInputField;
    private JLabel usernameErrorField;
    private JPasswordField passwordInputField;
    private JLabel passwordErrorField;
    private final JButton logInButton;
    private final JButton cancelButton;
    private final LoginViewModel loginViewModel;
    private final LoginController loginController;


    public LoginView(LoginViewModel loginViewModel, LoginController controller) {
        this.loginController = controller;
        this.loginViewModel = loginViewModel;

        this.loginViewModel.addPropertyChangeListener(this);
        // Create and do settings for frame
        frame = new JFrame();
        frame.setTitle(LoginViewModel.TITLE_LABEL);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setSize(LoginViewModel.FRAME_WIDTH_SIZE, LoginViewModel.FRAME_HEIGHT_SIZE);
        frame.setLocationRelativeTo(null);

        // Create and do settings for main panel
        panel = new JPanel();
        panel.setLayout(new FlowLayout(FlowLayout.CENTER, 150, 30));
        panel.setBackground(Color.lightGray);
        panel.setPreferredSize(LoginViewModel.PANEL_DIMENSION);
        frame.add(panel, BorderLayout.SOUTH);

        // Create a buffer sub-panel for a gap
        JPanel gapPanel = new JPanel();
        gapPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 100, 20));
        gapPanel.setBackground(Color.lightGray);
        panel.add(gapPanel, BorderLayout.SOUTH);

        // Create a sub-panel for the username text field
        JPanel usernameTextFieldPanel = new JPanel();
        usernameTextFieldPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 10));
        usernameTextFieldPanel.setBackground(Color.lightGray);
        panel.add(usernameTextFieldPanel, BorderLayout.SOUTH);

        // Create and do settings for the username text field
        usernameInputField = new JTextField(15);
        usernameInputField.setFont(LoginViewModel.INPUT_FIELD_FONT);
        usernameInputField.setToolTipText("Enter your username");
        usernameInputField.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ///
            }
        });
        JLabel usernameLabel = new JLabel(LoginViewModel.USERNAME_FIELD_LABEL);
        usernameLabel.setFont(LoginViewModel.INPUT_FIELD_FONT);
        usernameTextFieldPanel.add(usernameLabel);
        usernameTextFieldPanel.add(usernameInputField);

        // Create a sub-panel for the password text field
        JPanel passwordTextFieldPanel = new JPanel();
        passwordTextFieldPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 10));
        passwordTextFieldPanel.setBackground(Color.lightGray);
        panel.add(passwordTextFieldPanel, BorderLayout.SOUTH);

        // Create and do settings for the password text field
        passwordInputField = new JPasswordField(15);
        passwordInputField.setFont(LoginViewModel.INPUT_FIELD_FONT);
        passwordInputField.setToolTipText("Enter your password");
        passwordInputField.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ///
            }
        });
        JLabel passwordLabel = new JLabel(LoginViewModel.PASSWORD_FIELD_LABEL);
        passwordLabel.setFont(LoginViewModel.INPUT_FIELD_FONT);
        passwordTextFieldPanel.add(passwordLabel);
        passwordTextFieldPanel.add(passwordInputField);

        // Create a sub-panel for the buttons
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
        buttonPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 150, 70));
        buttonPanel.setBackground(Color.lightGray);
        panel.add(buttonPanel, BorderLayout.CENTER);

        // Create and do settings for the login button
        logInButton = new JButton(LoginViewModel.LOGIN_BUTTON_LABEL);
        logInButton.setFont(LoginViewModel.BUTTON_FONT);
        logInButton.setFocusable(false);
        logInButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (e.getSource().equals(logInButton)) {
                    LoginState currentState = loginViewModel.getState();

                    loginController.execute(
                            currentState.getUsername(),
                            currentState.getPassword(),
                            currentState.isDoctor()
                    );
                }
            }
        });
        logInButton.setPreferredSize(LoginViewModel.BUTTON_DIMENSION);
        buttonPanel.add(logInButton);

        // Create and do settings for the cancel button
        cancelButton = new JButton(LoginViewModel.CANCEL_BUTTON_LABEL);
        cancelButton.setFont(LoginViewModel.BUTTON_FONT);
        cancelButton.setFocusable(false);

        buttonPanel.add(cancelButton);

        cancelButton.addActionListener(this);


        usernameInputField.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
                LoginState currentState = loginViewModel.getState();
                currentState.setUsername(usernameInputField.getText() + e.getKeyChar());
                loginViewModel.setState(currentState);
            }

            @Override
            public void keyPressed(KeyEvent e) {
            }

            @Override
            public void keyReleased(KeyEvent e) {
            }
        });

        passwordInputField.addKeyListener(
                new KeyListener() {
                    @Override
                    public void keyTyped(KeyEvent e) {
                        LoginState currentState = loginViewModel.getState();
                        currentState.setPassword(passwordInputField.getText() + e.getKeyChar());
                        loginViewModel.setState(currentState);
                    }

                    @Override
                    public void keyPressed(KeyEvent e) {
                    }

                    @Override
                    public void keyReleased(KeyEvent e) {
                    }
                });
    }

    public void show(){
        frame.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        System.out.println("Click " + e.getActionCommand());
    }

    /**
     * React to a button click that results in evt.
     */


    @Override
    public void propertyChange(PropertyChangeEvent e) {
        LoginState state = (LoginState) e.getNewValue();
        setFields(state);
    }

    private void setFields(LoginState state) {
        usernameInputField.setText(state.getUsername());
    }

}


