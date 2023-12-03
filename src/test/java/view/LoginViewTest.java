package view;

import interface_adapter.login.LoginController;
import interface_adapter.login.LoginState;
import interface_adapter.login.LoginViewModel;
import interface_adapter.swap_views.welcome.SwapToWelcomeController;
import org.junit.Before;
import org.junit.Test;
import use_case.login.LoginInputBoundary;
import use_case.login.LoginInputData;
import use_case.swap_views.welcome.SwapToWelcomeData;
import use_case.swap_views.welcome.SwapToWelcomeInputBoundary;

import javax.swing.*;
import java.lang.reflect.Field;

import static org.junit.Assert.*;

public class LoginViewTest {

    private LoginView loginView;
    private LoginViewModel loginViewModel;

    @Before
    public void setUp() {
        LoginController loginController = new LoginController(new LoginInputBoundary() {
            @Override
            public void execute(LoginInputData loginInputData) {
                assertEquals("T", loginInputData.getUsername());
                assertEquals("P", loginInputData.getPassword());
                assertFalse(loginInputData.isDoctor());
            }
        });

        loginViewModel = new LoginViewModel();


        SwapToWelcomeController swapToWelcomeController = new SwapToWelcomeController(new SwapToWelcomeInputBoundary() {
            @Override
            public void execute(SwapToWelcomeData swapToWelcomeInputData) {
                assert true;
            }
        });

        // Instantiate the view
        loginView = new LoginView(loginViewModel, loginController, swapToWelcomeController);
        LoginState state = loginViewModel.getState();
        state.setUsername("T");
        state.setPassword("P");
        state.setDoctor(false);
    }



    @Test
    public void testLoginButton() {
        // Verify login button
        JButton logInButton = getField(loginView, JButton.class, "logInButton");
        assertNotNull(logInButton);


        // Simulate a click on the login button
        logInButton.doClick();

    }

    private <T> T getField(LoginView loginView, Class<T> fieldType, String fieldName) {
        for (Field field : loginView.getClass().getDeclaredFields()) {
            if (field.getType().equals(fieldType) && field.getName().equals(fieldName)) {
                try {
                    field.setAccessible(true);
                    return fieldType.cast(field.get(loginView));
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
        }
        return null;
    }



}



