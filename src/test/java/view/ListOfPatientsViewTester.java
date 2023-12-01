package view;

import interface_adapter.choosepatient.ChoosePatientController;
import interface_adapter.choosepatient.ChoosePatientViewModel;
import interface_adapter.swap_views.load_patients.LoadPatientsController;
import interface_adapter.swap_views.update.doctor.SwapToDoctorUpdateController;
import interface_adapter.swap_views.welcome.SwapToWelcomeController;
import org.junit.Before;
import org.junit.Test;
import use_case.choosepatient.ChoosePatientInputBoundary;
import use_case.choosepatient.ChoosePatientInputData;
import use_case.load_patient.LoadPatientInputBoundary;
import use_case.swap_views.update.doctor.SwapToDoctorUpdateInputBoundary;
import use_case.swap_views.welcome.SwapToWelcomeInputBoundary;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class ListOfPatientsViewTester {

    private ListOfPatientsView listOfPatientsView;

    @Before
    public void setUp() {
        // Initialize mock objects for the controllers and view model
        ChoosePatientController choosePatientController = new ChoosePatientController(new ChoosePatientInputBoundary() {
            @Override
            public void execute(ChoosePatientInputData choosePatientInputData) {
                // empty body
            }
        });

        ChoosePatientViewModel choosePatientViewModel = new ChoosePatientViewModel();

        SwapToWelcomeController swapToWelcomeController = new SwapToWelcomeController(new SwapToWelcomeInputBoundary() {
            @Override
            public void execute() {
                // empty body
            }
        });

        LoadPatientsController loadPatientsController = new LoadPatientsController(new LoadPatientInputBoundary() {
            public List<String> execute() {
                List<String> tester = new ArrayList<>();
                tester.add("Patient1");
                tester.add("Patient2");
                return tester;
            }
        });

        SwapToDoctorUpdateController swapToDoctorUpdateController = new SwapToDoctorUpdateController(new SwapToDoctorUpdateInputBoundary() {
            @Override
            public void execute(String name) {
                // empty body
            }
        });

        // Instantiate the view
        listOfPatientsView = new ListOfPatientsView(choosePatientController,
                choosePatientViewModel,
                swapToWelcomeController,
                loadPatientsController,
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

        modifyButton.doClick();

    }

    public void testMiddlePanel(){
        // Verify midPanel within scrollPane
        JScrollPane scrollPane = (JScrollPane) listOfPatientsView.getComponent(1);
        JPanel midPanel = (JPanel) scrollPane.getViewport().getView();

        assertNotNull(midPanel); // midpanel exists
        assertEquals(2, midPanel.getComponentCount()); // Two buttons as defined in your mock patients list

        // Checking if buttons exist
        JButton patient1Button = null;
        for (Component component : midPanel.getComponents()) {
            if (component instanceof JButton && "Patient1".equals(((JButton) component).getText())) {
                patient1Button = (JButton) component;
                break;
            }
        }

        patient1Button.doClick(); // just need to check that it does not raise an error
    }
}
