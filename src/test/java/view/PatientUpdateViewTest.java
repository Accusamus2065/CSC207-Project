package view;

import interface_adapter.swap_views.chatbot.SwapToDialogflowController;
import interface_adapter.update.patient.PatientUpdateController;
import interface_adapter.update.patient.PatientUpdateState;
import interface_adapter.update.patient.PatientUpdateViewModel;
import org.junit.Before;
import org.junit.Test;
import use_case.swap_views.chatbot.SwapToDialogflowInputBoundary;
import use_case.update.patient.PatientUpdateInputBoundary;
import use_case.update.patient.PatientUpdateInputData;

import javax.swing.*;
import javax.swing.text.JTextComponent;
import java.lang.reflect.Field;

public class PatientUpdateViewTest {
    PatientUpdateView patientUpdateView;
    PatientUpdateViewModel patientUpdateViewModel;

    @Before
    public void setup(){
        SwapToDialogflowController swapToDialogflowController = new SwapToDialogflowController(new SwapToDialogflowInputBoundary() {
            @Override
            public void execute() {
                // null
            }
        });
        PatientUpdateController patientUpdateController = new PatientUpdateController(new PatientUpdateInputBoundary() {
            @Override
            public void execute(PatientUpdateInputData patientUpdateInputData) {
                //
            }
        });

        patientUpdateViewModel = new PatientUpdateViewModel();

        patientUpdateView = new PatientUpdateView(patientUpdateViewModel, swapToDialogflowController, patientUpdateController);

    }


    @Test
    public void testBackButton() {
        // Simulate a click on the backButton
        patientUpdateView.getBackButton().doClick();

        // Verify that the swapController's execute method is called
        verify(swapController).execute();
    }

    @Test
    public void testSaveButton() {
        // Set values in the state
        PatientUpdateViewModel viewModel = patientUpdateViewModel;
        PatientUpdateViewModel.PatientUpdateState state = viewModel.getState();
        state.setUsername("CurrentUsername");
        state.setNewUsername("NewUsername");
        state.setPassword("Password");
        state.setRepeatPassword("RepeatPassword");
        state.setSex("Male");
        state.setGender("Other");
        state.setHeight("180");
        state.setWeight("75");
        state.setBloodType("AB+");
        viewModel.setState(state);

        // Simulate a click on the saveButton
        patientUpdateView.getSaveButton().doClick();

        // Verify that the updateController's execute method is called with the correct data
        verify(updateController).execute("CurrentUsername", "NewUsername", "Password", "RepeatPassword",
                "Male", "Other", "180", "75", "AB+");
    }

    }

    private <T> T getField(PatientUpdateView patientUpdateView, Class<T> fieldType, String fieldName) {
        for (Field field : patientUpdateView.getClass().getDeclaredFields()) {
            if (field.getType().equals(fieldType) && field.getName().equals(fieldName)) {
                try {
                    field.setAccessible(true);
                    return fieldType.cast(field.get(patientUpdateView));
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
        }
        return null;
    }


}
