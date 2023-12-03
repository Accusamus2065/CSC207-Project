package view;

import interface_adapter.login.LoginViewModel;
import interface_adapter.login.LoginController;
import interface_adapter.login.LoginState;
import interface_adapter.swap_views.welcome.SwapToWelcomeController;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class LoginView extends JPanel implements ActionListener, PropertyChangeListener {
    public final String viewName;
    private final JTextField usernameInputField;
    private final JPasswordField passwordInputField;
    private final JButton logInButton;
    private final JButton cancelButton;


    public LoginView(LoginViewModel loginViewModel, LoginController loginController, SwapToWelcomeController swapController) {
        this.viewName = loginViewModel.getViewName();
        loginViewModel.addPropertyChangeListener(this);

        // Create and do settings for main panel
        this.setLayout(new FlowLayout(FlowLayout.CENTER, 150, 30));
        this.setBackground(Color.lightGray);
        this.setPreferredSize(LoginViewModel.PANEL_DIMENSION);

        // Create a buffer sub-panel for a gap
        JPanel gapPanel = new JPanel();
        gapPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 100, 20));
        gapPanel.setBackground(Color.lightGray);
        this.add(gapPanel, BorderLayout.SOUTH);

        // Create a sub-panel for the username text field
        JPanel usernameTextFieldPanel = new JPanel();
        usernameTextFieldPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 10));
        usernameTextFieldPanel.setBackground(Color.lightGray);
        this.add(usernameTextFieldPanel, BorderLayout.SOUTH);

        // Create and do settings for the username text field
        usernameInputField = new JTextField(15);
        usernameInputField.setFont(LoginViewModel.INPUT_FIELD_FONT);
        usernameInputField.setToolTipText("Enter your username");
        usernameInputField.addActionListener(e -> {
        });
        JLabel usernameLabel = new JLabel(LoginViewModel.USERNAME_FIELD_LABEL);
        usernameLabel.setFont(LoginViewModel.INPUT_FIELD_FONT);
        usernameTextFieldPanel.add(usernameLabel);
        usernameTextFieldPanel.add(usernameInputField);

        // Create a sub-panel for the password text field
        JPanel passwordTextFieldPanel = new JPanel();
        passwordTextFieldPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 10));
        passwordTextFieldPanel.setBackground(Color.lightGray);
        this.add(passwordTextFieldPanel, BorderLayout.SOUTH);

        // Create and do settings for the password text field
        passwordInputField = new JPasswordField(15);
        passwordInputField.setFont(LoginViewModel.INPUT_FIELD_FONT);
        passwordInputField.setToolTipText("Enter your password");
        passwordInputField.addActionListener(e -> {
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
        this.add(buttonPanel, BorderLayout.CENTER);

        // Create and do settings for the login button
        logInButton = new JButton(LoginViewModel.LOGIN_BUTTON_LABEL);
        logInButton.setFont(LoginViewModel.BUTTON_FONT);
        logInButton.setFocusable(false);
        logInButton.addActionListener(e -> {
            if (e.getSource().equals(logInButton)) {
                LoginState currentState = loginViewModel.getState();

                loginController.execute(
                        currentState.getUsername(),
                        currentState.getPassword(),
                        currentState.isDoctor()
                );

                usernameInputField.setText("");
                passwordInputField.setText("");
            }
        });
        logInButton.setPreferredSize(LoginViewModel.BUTTON_DIMENSION);
        buttonPanel.add(logInButton);

        // Create and do settings for the cancel button
        cancelButton = new JButton(LoginViewModel.CANCEL_BUTTON_LABEL);
        cancelButton.setFont(LoginViewModel.BUTTON_FONT);
        cancelButton.setFocusable(false);
        cancelButton.setPreferredSize(LoginViewModel.BUTTON_DIMENSION);

        buttonPanel.add(cancelButton);

        cancelButton.addActionListener(e -> {
            swapController.execute(this.viewName);
            usernameInputField.setText("");
            passwordInputField.setText("");
        });


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

    @Override
    public void actionPerformed(ActionEvent e) {
        System.out.println("Click " + e.getActionCommand());
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        LoginState state = (LoginState) evt.getNewValue();
        if (state.getError() != null) {
            JOptionPane.showMessageDialog(this, state.getError());
        }
    }
}

