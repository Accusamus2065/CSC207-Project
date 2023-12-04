package view;

import interface_adapter.swap_views.chatbot.SwapToDialogflowController;
import interface_adapter.update.patient.PatientUpdateController;
import interface_adapter.update.patient.PatientUpdateViewModel;
import org.junit.Before;
import org.junit.Test;
import use_case.swap_views.chatbot.SwapToDialogflowInputBoundary;

import use_case.update.patient.PatientUpdateInputBoundary;
import use_case.update.patient.PatientUpdateInputData;

import javax.swing.*;
import java.awt.*;

import static org.junit.Assert.*;

public class PatientUpdateViewTest {

    private PatientUpdateView patientUpdateView;

    @Before
    public void setUp() {
        PatientUpdateViewModel patientUpdateViewModel = new PatientUpdateViewModel();
        SwapToDialogflowController swapController = new SwapToDialogflowController(new SwapToDialogflowInputBoundary() {
            @Override
            public void execute() {
                assert true;
            }
        });
        PatientUpdateController updateController = new PatientUpdateController(new PatientUpdateInputBoundary() {
            @Override
            public void execute(PatientUpdateInputData patientUpdateInputData) {
                assert true;
            }
        });
        patientUpdateView = new PatientUpdateView(patientUpdateViewModel, swapController, updateController);
    }

    @Test
    public void testComponentsExist() {
        assertEquals(10, patientUpdateView.getComponentCount());
        assertTrue(patientUpdateView.getComponent(0) instanceof JPanel);
        assertTrue(patientUpdateView.getComponent(1) instanceof JPanel);
        assertTrue(patientUpdateView.getComponent(2) instanceof JPanel);
        assertTrue(patientUpdateView.getComponent(3) instanceof JPanel);
        assertTrue(patientUpdateView.getComponent(4) instanceof JPanel);
        assertTrue(patientUpdateView.getComponent(5) instanceof JPanel);
        assertTrue(patientUpdateView.getComponent(6) instanceof JPanel);
        assertTrue(patientUpdateView.getComponent(7) instanceof JPanel);
        assertTrue(patientUpdateView.getComponent(8) instanceof JPanel);
        assertTrue(patientUpdateView.getComponent(9) instanceof JPanel);

        // Verify backButton
        JPanel upperPanel = (JPanel) patientUpdateView.getComponent(0);
        Component[] upperPanelComponents = upperPanel.getComponents();
        JButton backButton = null;
        for (Component component : upperPanelComponents) {
            if (component instanceof JButton && "BACK".equals(((JButton) component).getText())) {
                backButton = (JButton) component;
                break;
            }
        }
        assertNotNull(backButton);

        // Verify username text field
        JPanel usernamePanel = (JPanel) patientUpdateView.getComponent(1);
        Component[] usernamePanelComponents = usernamePanel.getComponents();
        JTextField usernameField = null;
        for (Component component : usernamePanelComponents) {
            if (component instanceof JTextField) {
                usernameField = (JTextField) component;
                break;
            }
        }
        assertNotNull(usernameField);

        // Verify saveButton
        JPanel lowerPanel = (JPanel) patientUpdateView.getComponent(9);
        Component[] lowerPanelComponents = lowerPanel.getComponents();
        JButton saveButton = null;
        for (Component component : lowerPanelComponents) {
            if (component instanceof JButton && "SAVE".equals(((JButton) component).getText())) {
                saveButton = (JButton) component;
                break;
            }
        }
        assertNotNull(saveButton);
    }

}
