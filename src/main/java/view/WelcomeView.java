package view;

import interface_adapter.welcome.WelcomeViewModel;
import interface_adapter.welcome.WelcomeState;
import interface_adapter.welcome.login.WelcomeLoginController;
import interface_adapter.welcome.signup.WelcomeSignupController;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class WelcomeView extends JPanel implements ActionListener{
    public final String viewName = "welcome";
    private final JButton logInButton;
    private final JButton signUpButton;
    private final JLabel label;
    private final JCheckBox checkBox;

    public WelcomeView(WelcomeViewModel welcomeViewModel,
                       WelcomeSignupController signupController,
                       WelcomeLoginController loginController) {
        // Create and do settings for main panel
        this.setLayout(new FlowLayout(FlowLayout.CENTER, 100, 65));
        this.setBackground(Color.lightGray);
        this.setPreferredSize(WelcomeViewModel.PANEL_DIMENSION);

        // Create and add label to main panel
        label = new JLabel("BENSON");
        label.setFont(new Font("Sans-serif", Font.BOLD, 70));
        this.add(label);

        // Create a subpanel for the checkbox
        JPanel checkBoxPanel = new JPanel();
        checkBoxPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 150, 10));
        checkBoxPanel.setBackground(Color.lightGray);
        this.add(checkBoxPanel, BorderLayout.SOUTH);

        // Create and add checkbox to main panel indicating whether the user is a doctor
        checkBox = new JCheckBox();
        checkBox.setText(WelcomeViewModel.DOCTOR_CHECKBOX_LABEL);
        checkBox.setFont(new Font("Sans-serif", Font.PLAIN, 18));
        checkBox.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10)); // Add padding
        checkBoxPanel.add(checkBox);
        checkBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (e.getSource().equals(checkBox)) {
                            WelcomeState welcomeState = welcomeViewModel.getState();
                            welcomeState.setDoctor(!welcomeState.isDoctor());
                            welcomeViewModel.firePropertyChanged();
                        }
            }
        });

        // Create a subpanel for the buttons
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
        buttonPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 150, 3));
        buttonPanel.setBackground(Color.lightGray);
        this.add(buttonPanel, BorderLayout.CENTER);

        // Create and do settings for login button
        logInButton = new JButton(WelcomeViewModel.LOGIN_BUTTON_LABEL);
        logInButton.setFont(WelcomeViewModel.BUTTON_FONT);
        logInButton.setFocusable(false);
        logInButton.addActionListener(
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        WelcomeState welcomeState = welcomeViewModel.getState();
                        loginController.execute(welcomeState.isDoctor());
                    }
                });
        logInButton.setPreferredSize(WelcomeViewModel.BUTTON_DIMENSION);
        buttonPanel.add(logInButton);

        // Create and do settings for signup button
        signUpButton = new JButton(WelcomeViewModel.SIGNUP_BUTTON_LABEL);
        signUpButton.setFont(WelcomeViewModel.BUTTON_FONT);
        signUpButton.setFocusable(false);
        signUpButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                WelcomeState welcomeState = welcomeViewModel.getState();
                signupController.execute(welcomeState.isDoctor());
            }
        });
        signUpButton.setPreferredSize(WelcomeViewModel.BUTTON_DIMENSION);
        buttonPanel.add(signUpButton);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JOptionPane.showConfirmDialog(this, "Not implemented yet.");
    }
}
