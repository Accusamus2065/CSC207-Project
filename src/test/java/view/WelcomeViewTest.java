package view;

import interface_adapter.welcome.WelcomeViewModel;
import interface_adapter.welcome.login.WelcomeLoginController;
import interface_adapter.welcome.signup.WelcomeSignupController;
import org.junit.Before;
import org.junit.Test;
import use_case.welcome.WelcomeInputData;
import use_case.welcome.login.WelcomeLoginInputBoundary;
import use_case.welcome.signup.WelcomeSignupInputBoundary;

import javax.swing.*;
import java.awt.*;


import static org.junit.Assert.*;

public class WelcomeViewTest {

    private WelcomeView welcomeView;

    private WelcomeViewModel welcomeViewModel;

    @Before
    public void setUp() {
        WelcomeSignupController signupController = new WelcomeSignupController(new WelcomeSignupInputBoundary() {
            @Override
            public void execute(WelcomeInputData welcomeInputData) {
                assert welcomeInputData.isDoctor();
        }});

        WelcomeLoginController loginController = new WelcomeLoginController(new WelcomeLoginInputBoundary() {
            @Override
            public void execute(WelcomeInputData welcomeInputData) {
                assert welcomeInputData.isDoctor();

        }});

        welcomeViewModel = new WelcomeViewModel();

        welcomeView = new WelcomeView(welcomeViewModel, signupController, loginController);
    }

    @Test
    public void testWelcomeView() {
        // Verify components
        assertEquals(3, welcomeView.getComponentCount()); // Three components: label, checkbox panel, and button panel

        JPanel checkBoxPanel = (JPanel) welcomeView.getComponent(1);
        Component[] checkBoxPanelComponents = checkBoxPanel.getComponents();
        JCheckBox checkBox = null;
        for (Component component : checkBoxPanelComponents) {
            if (component instanceof JCheckBox) {
                checkBox = (JCheckBox) component;
                break;
            }
        }
        assertNotNull(checkBox);

        checkBox.doClick();

        assertTrue(welcomeViewModel.getState().isDoctor());

        // Verify buttons
        JPanel buttonPanel = (JPanel) welcomeView.getComponent(2);
        Component[] buttonPanelComponents = buttonPanel.getComponents();
        JButton loginButton = null;
        JButton signupButton = null;
        for (Component component : buttonPanelComponents) {
            if (component instanceof JButton button) {
                if ("Log In".equals(button.getText())) {
                    loginButton = button;
                } else if ("Sign Up".equals(button.getText())) {
                    signupButton = button;
                }
            }
        }
        assertNotNull(loginButton);
        assertNotNull(signupButton);

        // Simulate a click on the login button
        loginButton.doClick();

        // Simulate a click on the signup button
        signupButton.doClick();
    }
}
