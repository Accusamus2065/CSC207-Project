package view;

import interface_adapter.choose_patient.ChoosePatientController;
import interface_adapter.choose_patient.ChoosePatientState;
import interface_adapter.choose_patient.ChoosePatientViewModel;
import interface_adapter.swap_views.chat.SwapToConversationController;
import interface_adapter.load_patients.LoadPatientController;
import interface_adapter.swap_views.login.SwapToLoginController;
import interface_adapter.swap_views.update.doctor.SwapToDoctorUpdateController;
import org.junit.Before;
import org.junit.Test;

import use_case.swap_views.login.SwapToLoginInputBoundary;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class ListOfPatientsViewTest {

    private ListOfPatientsView listOfPatientsView;

    private String username;

    @Before
    public void setUp() {
        // Initialize mock objects for the controllers and view model
        ChoosePatientController choosePatientController = new ChoosePatientController(
                choosePatientInputData -> assertEquals("Patient1", choosePatientInputData.getPatient())
        );

        ChoosePatientViewModel choosePatientViewModel = new ChoosePatientViewModel();
        ChoosePatientState state = choosePatientViewModel.getState();
        state.setUsername("TestUsername");
        username = choosePatientViewModel.getState().getUsername();

        SwapToLoginController swapToLoginController = new SwapToLoginController(new SwapToLoginInputBoundary() {
            @Override
            public void execute() {
                assert true;
            }
        });

        LoadPatientController loadPatientController = new LoadPatientController(() -> {
            List<String> tester = new ArrayList<>();
            tester.add("Patient1");
            tester.add("Patient2");
            return tester;
        });

        SwapToConversationController swapToConversationController = new SwapToConversationController(
                (sender, receiver) -> {
                    assert true;
                });

        SwapToDoctorUpdateController swapToDoctorUpdateController = new SwapToDoctorUpdateController(
                name -> assertEquals(name, username)
        );

        // Instantiate the view
        listOfPatientsView = new ListOfPatientsView(choosePatientController,
                choosePatientViewModel,
                swapToLoginController,
                swapToConversationController,
                loadPatientController,
                swapToDoctorUpdateController);
    }

    @Test
    public void testUpperPanel() {
        // Verify components
        assertEquals(2, listOfPatientsView.getComponentCount()); // Two components: upperPanel and scrollPane

        // Verify modifyButton
        JPanel upperPanel = (JPanel) listOfPatientsView.getComponent(0);
        Component[] upperPanelComponents = upperPanel.getComponents();
        JButton modifyButton = null;
        for (Component component : upperPanelComponents) {
            if (component instanceof JButton && "Modify Profile".equals(((JButton) component).getText())) {
                modifyButton = (JButton) component;
                break;
            }
        }
        assertNotNull(modifyButton);

        // Testing if the controlled receives the correct data
        modifyButton.doClick();

    }

    @Test
    public void testMiddlePanel() {
        // Verify midPanel within scrollPane
        JScrollPane scrollPane = (JScrollPane) listOfPatientsView.getComponent(1);
        JPanel midPanel = (JPanel) scrollPane.getViewport().getView();

        assertNotNull(midPanel); // midpanel exists

        // Testing if the loadpatients execute method worked correctly
        assertEquals(2, midPanel.getComponentCount()); // Two buttons as defined in your mock patients list

        // Checking if buttons exist
        JButton patient1Button = null;
        for (Component component : midPanel.getComponents()) {
            if (component instanceof JButton && "Patient1".equals(((JButton) component).getText())) {
                patient1Button = (JButton) component;
                break;
            }
        }

        patient1Button.doClick();

    }
}
