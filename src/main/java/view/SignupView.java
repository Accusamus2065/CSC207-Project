package view;

import interface_adapter.signup.SignupController;
import interface_adapter.signup.SignupState;
import interface_adapter.signup.SignupViewModel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class SignupView extends JPanel implements ActionListener, PropertyChangeListener {
    public final String viewName = "sign up";
    private final JButton signUp;

    public SignupView(SignupViewModel signupViewModel, SignupController signupController) {

        // Create and do settings for frame
        frame = new JFrame();
        frame.setTitle(SignupViewModel.TITLE_LABEL);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setSize(SignupViewModel.FRAME_WIDTH_SIZE, SignupViewModel.FRAME_HEIGHT_SIZE);
        frame.setLocationRelativeTo(null);

        // Create and do settings for main panel
        panel = new JPanel();
        panel.setLayout(new FlowLayout(FlowLayout.CENTER, 150, 30));
        panel.setBackground(Color.lightGray);
        panel.setPreferredSize(SignupViewModel.PANEL_DIMENSION);
        frame.add(panel, BorderLayout.SOUTH);

        // Create a buffer sub-panel for a gap
        JPanel gapPanel = new JPanel();
        gapPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 100, 13));
        gapPanel.setBackground(Color.lightGray);
        panel.add(gapPanel, BorderLayout.SOUTH);

        // Create a sub-panel for the username text field
        JPanel usernameTextFieldPanel = new JPanel();
        usernameTextFieldPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 7));
        usernameTextFieldPanel.setBackground(Color.lightGray);
        panel.add(usernameTextFieldPanel, BorderLayout.SOUTH);

        // Create and do settings for the username text field
        usernameInputField = new JTextField(15);
        usernameInputField.setFont(SignupViewModel.INPUT_FIELD_FONT);
        usernameInputField.setToolTipText("Enter your username");
        usernameInputField.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ///
            }
        });
        JLabel usernameLabel = new JLabel(SignupViewModel.USERNAME_FIELD_LABEL);
        usernameLabel.setFont(SignupViewModel.INPUT_FIELD_FONT);
        usernameTextFieldPanel.add(usernameLabel);
        usernameTextFieldPanel.add(usernameInputField);

        // Create a sub-panel for the password text field
        JPanel passwordTextFieldPanel = new JPanel();
        passwordTextFieldPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 7));
        passwordTextFieldPanel.setBackground(Color.lightGray);
        panel.add(passwordTextFieldPanel, BorderLayout.SOUTH);

        // Create and do settings for the password text field
        passwordInputField = new JPasswordField(15);
        passwordInputField.setFont(SignupViewModel.INPUT_FIELD_FONT);
        passwordInputField.setToolTipText("Enter your password");
        passwordInputField.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ///
            }
        });
        JLabel passwordLabel = new JLabel(SignupViewModel.PASSWORD_FIELD_LABEL);
        passwordLabel.setFont(SignupViewModel.INPUT_FIELD_FONT);
        passwordTextFieldPanel.add(passwordLabel);
        passwordTextFieldPanel.add(passwordInputField);

        // Create a sub-panel for the repeat password text field
        JPanel repeatPasswordTextFieldPanel = new JPanel();
        repeatPasswordTextFieldPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 7));
        repeatPasswordTextFieldPanel.setBackground(Color.lightGray);
        panel.add(repeatPasswordTextFieldPanel, BorderLayout.SOUTH);

        // Create and do settings for the repeat password text field
        repeatPasswordInputField = new JPasswordField(15);
        repeatPasswordInputField.setFont(SignupViewModel.INPUT_FIELD_FONT);
        repeatPasswordInputField.setToolTipText("Re-enter your password");
        repeatPasswordInputField.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ///
            }
        });
        JLabel repeatPasswordLabel = new JLabel(SignupViewModel.REPEAT_PASSWORD_FIELD_LABEL);
        repeatPasswordLabel.setFont(SignupViewModel.INPUT_FIELD_FONT);
        repeatPasswordTextFieldPanel.add(repeatPasswordLabel);
        repeatPasswordTextFieldPanel.add(repeatPasswordInputField);

        // Create a sub-panel for the buttons
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
        buttonPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 150, 40));
        buttonPanel.setBackground(Color.lightGray);
        panel.add(buttonPanel, BorderLayout.CENTER);

        // Create and do settings for the login button
        signUpButton = new JButton(SignupViewModel.SIGNUP_BUTTON_LABEL);
        signUpButton.setFont(SignupViewModel.BUTTON_FONT);
        signUpButton.setFocusable(false);
        signUpButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {}
        });
        signUpButton.setPreferredSize(SignupViewModel.BUTTON_DIMENSION);
        buttonPanel.add(signUpButton);

        // Create and do settings for the cancel button
        cancelButton = new JButton(SignupViewModel.CANCEL_BUTTON_LABEL);
        cancelButton.setFont(SignupViewModel.BUTTON_FONT);
        cancelButton.setFocusable(false);
        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {}
        });
        cancelButton.setPreferredSize(SignupViewModel.BUTTON_DIMENSION);
        buttonPanel.add(cancelButton);

//         signupViewModel.addPropertyChangeListener(this);
//        JLabel title = new JLabel(SignupViewModel.TITLE_LABEL);
//        title.setAlignmentX(Component.CENTER_ALIGNMENT);

//        JTextField usernameInputField = new JTextField(15);
//        LabelTextPanel usernameInfo = new LabelTextPanel(
//                new JLabel(SignupViewModel.USERNAME_LABEL), usernameInputField);
//        JPasswordField passwordInputField = new JPasswordField(15);
//        LabelTextPanel passwordInfo = new LabelTextPanel(
//                new JLabel(SignupViewModel.PASSWORD_LABEL), passwordInputField);
//        JPasswordField repeatPasswordInputField = new JPasswordField(15);
//        LabelTextPanel repeatPasswordInfo = new LabelTextPanel(
//                new JLabel(SignupViewModel.REPEAT_PASSWORD_LABEL), repeatPasswordInputField);

//        JPanel buttons = new JPanel();
//        signUp = new JButton(SignupViewModel.SIGNUP_BUTTON_LABEL);
//        buttons.add(signUp);

        signUpButton.addActionListener(
                evt -> {
                    if (evt.getSource().equals(signUpButton)) {
                        SignupState currentState = signupViewModel.getState();

                        signupController.execute(
                                currentState.getUsername(),
                                currentState.getPassword(),
                                currentState.getPasswordError(),
                                currentState.isDoctor()
                        );
                    }
                }
        );

        usernameInputField.addKeyListener(
                new KeyListener() {
                    @Override
                    public void keyTyped(KeyEvent e) {
                        SignupState currentState = signupViewModel.getState();
                        String text = usernameInputField.getText() + e.getKeyChar();
                        currentState.setUsername(text);
                        signupViewModel.setState(currentState);
                    }

                    @Override
                    public void keyPressed(KeyEvent e) {

                    }

                    @Override
                    public void keyReleased(KeyEvent e) {

                    }
                }
        );

        passwordInputField.addKeyListener(
                new KeyListener() {
                    @Override
                    public void keyTyped(KeyEvent e) {
                        SignupState currentState = signupViewModel.getState();
                        String password = new String(passwordInputField.getPassword()) + e.getKeyChar();
                        currentState.setPassword(password);
                        signupViewModel.setState(currentState);
                    }

                    @Override
                    public void keyPressed(KeyEvent e) {

                    }

                    @Override
                    public void keyReleased(KeyEvent e) {

                    }
                }
        );

        repeatPasswordInputField.addKeyListener(
                new KeyListener() {
                    @Override
                    public void keyTyped(KeyEvent e) {
                        SignupState currentState = signupViewModel.getState();
                        String repeatPassword = new String(repeatPasswordInputField.getPassword()) + e.getKeyChar();
                        currentState.setRepeatPassword(repeatPassword);
                        signupViewModel.setState(currentState);
                    }

                    @Override
                    public void keyPressed(KeyEvent e) {

                    }

                    @Override
                    public void keyReleased(KeyEvent e) {

                    }
                }
        );
//        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
//        this.add(title);
//        this.add(usernameInfo);
//        this.add(passwordInfo);
//        this.add(repeatPasswordInfo);
//        this.add(buttons);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        SignupState state = (SignupState) evt.getNewValue();
        if (state.getUsernameError() != null) {
            JOptionPane.showMessageDialog(this, state.getUsernameError());
        } else if (state.getPasswordError() != null) {
            JOptionPane.showMessageDialog(this, state.getPasswordError());
        }
    }
}
