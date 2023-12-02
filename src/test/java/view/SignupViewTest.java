package view;

import interface_adapter.signup.SignupController;
import interface_adapter.signup.SignupState;
import interface_adapter.signup.SignupViewModel;
import interface_adapter.swap_views.welcome.SwapToWelcomeController;
import org.junit.Before;
import org.junit.Test;

import use_case.signup.SignupInputBoundary;
import use_case.signup.SignupInputData;
import use_case.swap_views.welcome.SwapToWelcomeInputBoundary;

import javax.swing.*;
import java.lang.reflect.Field;

import static org.junit.Assert.*;

public class SignupViewTest {

    private SignupView signupView;
    private SignupViewModel signupViewModel;

    @Before
    public void setUp() {
        // Initialize mock objects for the controllers and view model
        SignupController signupController = new SignupController(new SignupInputBoundary() {
            @Override
            public void execute(SignupInputData signupInputData) {
                assertEquals("T", signupInputData.getUsername());
                assertEquals("P", signupInputData.getPassword());
                assertFalse(signupInputData.isDoctor());
            }
        });

        signupViewModel = new SignupViewModel();


        SwapToWelcomeController swapToWelcomeController = new SwapToWelcomeController(new SwapToWelcomeInputBoundary() {
            @Override
            public void execute() {
                assert true;
            }
        });

        // Instantiate the view
        signupView = new SignupView(signupViewModel, signupController, swapToWelcomeController);
        SignupState state = signupViewModel.getState();
        state.setUsername("T");
        state.setPassword("P");
        state.setDoctor(false);
    }



    @Test
    public void testSignupButton() {
        // Verify login button
        JButton signUpButton = getField(signupView, JButton.class, "signUpButton");
        assertNotNull(signUpButton);


        // Simulate a click on the login button
        signUpButton.doClick();

        // Verify that the login controller method is called with the correct data
        // The controller method is mocked in the setUp() method
    }

    private <T> T getField(SignupView signupView, Class<T> fieldType, String fieldName) {
        for (Field field : signupView.getClass().getDeclaredFields()) {
            if (field.getType().equals(fieldType) && field.getName().equals(fieldName)) {
                try {
                    field.setAccessible(true);
                    return fieldType.cast(field.get(signupView));
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
        }
        return null;
    }
}
