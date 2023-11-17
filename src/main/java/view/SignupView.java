package view;

import interface_adapter.signup.SignupController;
import interface_adapter.signup.SignupState;
import interface_adapter.signup.SignupViewModel;
import use_case.signup.SignupInputData;

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
    boolean isDoctor;
    private final JButton signUp;

    public SignupView(SignupViewModel signupViewModel, SignupController signupController, boolean isDoctor) {

        signupViewModel.addPropertyChangeListener(this);
        JLabel title = new JLabel(SignupViewModel.TITLE_LABEL);
        title.setAlignmentX(Component.CENTER_ALIGNMENT);

        JTextField usernameInputField = new JTextField(15);
        LabelTextPanel usernameInfo = new LabelTextPanel(
                new JLabel(SignupViewModel.USERNAME_LABEL), usernameInputField);
        JPasswordField passwordInputField = new JPasswordField(15);
        LabelTextPanel passwordInfo = new LabelTextPanel(
                new JLabel(SignupViewModel.PASSWORD_LABEL), passwordInputField);
        JPasswordField repeatPasswordInputField = new JPasswordField(15);
        LabelTextPanel repeatPasswordInfo = new LabelTextPanel(
                new JLabel(SignupViewModel.REPEAT_PASSWORD_LABEL), repeatPasswordInputField);

        JPanel buttons = new JPanel();
        signUp = new JButton(SignupViewModel.SIGNUP_BUTTON_LABEL);
        buttons.add(signUp);

        signUp.addActionListener(
                evt -> {
                    if (evt.getSource().equals(signUp)) {
                        SignupState currentState = signupViewModel.getState();

                        signupController.execute(
                                currentState.getUsername(),
                                currentState.getPassword(),
                                currentState.getPasswordError(),
                                isDoctor
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

        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        this.add(title);
        this.add(usernameInfo);
        this.add(passwordInfo);
        this.add(repeatPasswordInfo);
        this.add(buttons);
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
