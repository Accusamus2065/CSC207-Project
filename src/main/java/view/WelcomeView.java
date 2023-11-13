//package view;
//
//import interface_adapter.welcome.WelcomeState;
//import interface_adapter.welcome.WelcomeViewModel;
//import interface_adapter.welcome.login.WelcomeLoginController;
//import interface_adapter.welcome.signup.WelcomeSignupController;
//
//import javax.swing.*;
//import java.awt.*;
//import java.awt.event.ActionEvent;
//import java.awt.event.ActionListener;
//
//public class WelcomeView extends JPanel implements ActionListener {
//
//    public final String viewName = "Welcome";
//    private final WelcomeSignupController welcomeSignupController;
//    private final WelcomeLoginController welcomeLoginController;
//
//    private final JCheckBox isDoctor;
//    private final JButton signUp;
//    private final JButton logIn;
//
//    public WelcomeView(WelcomeViewModel welcomeViewModel,
//                       WelcomeSignupController signupController,
//                       WelcomeLoginController loginController) {
//
//        this.welcomeLoginController = loginController;
//        this.welcomeSignupController = signupController;
//
//        JLabel title = new JLabel(WelcomeViewModel.TITLE_LABEL);
//        title.setAlignmentX(Component.CENTER_ALIGNMENT);
//
//        JPanel doctorInformation = new JPanel();
//        isDoctor = new JCheckBox(WelcomeViewModel.DOCTOR_CHECKBOX_LABEL);
//        doctorInformation.add(isDoctor);
//
//        JPanel buttons = new JPanel();
//        signUp = new JButton(WelcomeViewModel.SIGNUP_BUTTON_LABEL);
//        buttons.add(signUp);
//        logIn = new JButton(WelcomeViewModel.LOGIN_BUTTON_LABEL);
//        buttons.add(logIn);
//
//        isDoctor.addActionListener(
//                new ActionListener() {
//                    @Override
//                    public void actionPerformed(ActionEvent e) {
//                        if (e.getSource().equals(isDoctor)) {
//                            WelcomeState welcomeState = welcomeViewModel.getState();
//                            welcomeState.setDoctor(!welcomeState.isDoctor());
//                            welcomeViewModel.firePropertyChanged();
//                        }
//                    }
//                }
//        );
//        signUp.addActionListener(
//                new ActionListener() {
//                    @Override
//                    public void actionPerformed(ActionEvent e) {
//                        if (e.getSource().equals(signUp)) {
//                            welcomeSignupController.execute(isDoctor.isSelected());
//                        }
//                    }
//                }
//        );
//        logIn.addActionListener(
//                new ActionListener() {
//                    @Override
//                    public void actionPerformed(ActionEvent e) {
//                        if (e.getSource().equals(logIn)) {
//                            welcomeLoginController.execute(isDoctor.isSelected());
//                        }
//                    }
//                }
//        );
//
//        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
//
//        this.add(title);
//        this.add(doctorInformation);
//        this.add(buttons);
//    }
//
//    @Override
//    public void actionPerformed(ActionEvent e) {
//        JOptionPane.showConfirmDialog(this, "Not implemented yet.");
//    }
//}

package view;

import interface_adapter.welcome.WelcomeViewModel;
import interface_adapter.welcome.WelcomeState;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class WelcomeView implements ActionListener{
    private JFrame frame;
    private JPanel panel;
    private JButton logInButton;
    private JButton signUpButton;
    private JLabel label;
    private JCheckBox checkBox;
    private WelcomeViewModel welcomeViewModel;

    public WelcomeView(JButton logInButton) {
        // Create and do settings for frame
        frame = new JFrame();
        frame.setTitle(welcomeViewModel.TITLE_LABEL);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setSize(welcomeViewModel.FRAME_WIDTH_SIZE, welcomeViewModel.FRAME_HEIGHT_SIZE);
        frame.setLocationRelativeTo(null);

        // Create and do settings for main panel
        panel = new JPanel();
        panel.setLayout(new FlowLayout(FlowLayout.CENTER, 100, 65));
        panel.setBackground(Color.lightGray);
        panel.setPreferredSize(welcomeViewModel.PANEL_DIMENSION);
        frame.add(panel, BorderLayout.SOUTH);

        // Create and add label to main panel
        label = new JLabel("BENSON");
        label.setFont(new Font("Sans-serif", Font.BOLD, 70));
        panel.add(label);

        // Create a subpanel for the checkbox
        JPanel checkBoxPanel = new JPanel();
        checkBoxPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 150, 10));
        checkBoxPanel.setBackground(Color.lightGray);
        panel.add(checkBoxPanel, BorderLayout.SOUTH);

        // Create and add checkbox to main panel indicating whether the user is a doctor
        checkBox = new JCheckBox();
        checkBox.setText(welcomeViewModel.DOCTOR_CHECKBOX_LABEL);
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
        panel.add(buttonPanel, BorderLayout.CENTER);

        // Create and do settings for login button
        logInButton = new JButton(welcomeViewModel.LOGIN_BUTTON_LABEL);
        logInButton.setFont(WelcomeViewModel.BUTTON_FONT);
        logInButton.setFocusable(false);
        logInButton.addActionListener(
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        System.out.println("Logged In");
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
                System.out.println("Signed In");
            }
        });
        signUpButton.setPreferredSize(WelcomeViewModel.BUTTON_DIMENSION);
        buttonPanel.add(signUpButton);

    }
    public void show() {
        frame.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
//        JOptionPane.showConfirmDialog(this, "Not implemented yet.");
    }
}
