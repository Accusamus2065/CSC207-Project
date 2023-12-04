package view;

import interface_adapter.swap_views.list_of_patients.SwapToPatientListController;
import interface_adapter.update.doctor.DoctorUpdateController;
import interface_adapter.update.doctor.DoctorUpdateViewModel;
import org.junit.Before;
import org.junit.Test;
import use_case.update.doctor.DoctorUpdateInputBoundary;
import use_case.update.doctor.DoctorUpdateInputData;

import javax.swing.*;
import java.awt.*;

import static org.junit.Assert.*;

public class DoctorUpdateViewTest {

    private DoctorUpdateView doctorUpdateView;

    @Before
    public void setUp() {
        DoctorUpdateViewModel doctorUpdateViewModel = new DoctorUpdateViewModel();
        SwapToPatientListController swapController = new SwapToPatientListController(() -> {
            // Add assertions or actions as needed
            assert true;
        });
        DoctorUpdateController updateController = new DoctorUpdateController(new DoctorUpdateInputBoundary() {
            @Override
            public void execute(DoctorUpdateInputData doctorUpdateInputData) {
                assert true;
            }
        });
        doctorUpdateView = new DoctorUpdateView(doctorUpdateViewModel, swapController, updateController);
    }

    @Test
    public void testComponentsExist() {
        assertEquals(7, doctorUpdateView.getComponentCount());
        assertTrue(doctorUpdateView.getComponent(0) instanceof JPanel);
        assertTrue(doctorUpdateView.getComponent(1) instanceof JPanel);
        assertTrue(doctorUpdateView.getComponent(2) instanceof JPanel);
        assertTrue(doctorUpdateView.getComponent(3) instanceof JPanel);
        assertTrue(doctorUpdateView.getComponent(4) instanceof JPanel);
        assertTrue(doctorUpdateView.getComponent(5) instanceof JPanel);
        assertTrue(doctorUpdateView.getComponent(6) instanceof JPanel);

        // Verify backButton
        JPanel upperPanel = (JPanel) doctorUpdateView.getComponent(0);
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
        JPanel usernamePanel = (JPanel) doctorUpdateView.getComponent(1);
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
        JPanel lowerPanel = (JPanel) doctorUpdateView.getComponent(6);
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
