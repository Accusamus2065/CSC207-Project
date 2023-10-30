package view;

import interface_adapter.welcome.WelcomeState;
import interface_adapter.welcome.WelcomeViewModel;
import interface_adapter.welcome.login.WelcomeLoginController;
import interface_adapter.welcome.signup.WelcomeSignupController;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class WelcomeView extends JPanel implements ActionListener {

    public final String viewName = "Welcome";
    private final WelcomeSignupController welcomeSignupController;
    private final WelcomeLoginController welcomeLoginController;

    private final JCheckBox isDoctor;
    private final JButton signUp;
    private final JButton logIn;

    public WelcomeView(WelcomeViewModel welcomeViewModel,
                       WelcomeSignupController signupController,
                       WelcomeLoginController loginController) {

        this.welcomeLoginController = loginController;
        this.welcomeSignupController = signupController;

        JLabel title = new JLabel(WelcomeViewModel.TITLE_LABEL);
        title.setAlignmentX(Component.CENTER_ALIGNMENT);

        JPanel doctorInformation = new JPanel();
        isDoctor = new JCheckBox(WelcomeViewModel.DOCTOR_CHECKBOX_LABEL);
        doctorInformation.add(isDoctor);

        JPanel buttons = new JPanel();
        signUp = new JButton(WelcomeViewModel.SIGNUP_BUTTON_LABEL);
        buttons.add(signUp);
        logIn = new JButton(WelcomeViewModel.LOGIN_BUTTON_LABEL);
        buttons.add(logIn);

        isDoctor.addActionListener(
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        if (e.getSource().equals(isDoctor)) {
                            WelcomeState welcomeState = welcomeViewModel.getState();
                            welcomeState.setDoctor(!welcomeState.isDoctor());
                            welcomeViewModel.firePropertyChanged();
                        }
                    }
                }
        );
        signUp.addActionListener(
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        if (e.getSource().equals(signUp)) {
                            welcomeSignupController.execute(isDoctor.isSelected());
                        }
                    }
                }
        );
        logIn.addActionListener(
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        if (e.getSource().equals(logIn)) {
                            welcomeLoginController.execute(isDoctor.isSelected());
                        }
                    }
                }
        );

        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        this.add(title);
        this.add(doctorInformation);
        this.add(buttons);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JOptionPane.showConfirmDialog(this, "Not implemented yet.");
    }
}
