package view;

import interface_adapter.choose_patient.ChoosePatientController;
import interface_adapter.choose_patient.ChoosePatientState;
import interface_adapter.choose_patient.ChoosePatientViewModel;
import interface_adapter.swap_views.chat.SwapToConversationController;
import interface_adapter.load_patients.LoadPatientController;
import interface_adapter.swap_views.login.SwapToLoginController;
import interface_adapter.swap_views.train.SwapToTrainingController;
import interface_adapter.swap_views.update.doctor.SwapToDoctorUpdateController;
import org.junit.Before;
import org.junit.Test;


import use_case.choose_patient.ChoosePatientInputBoundary;
import use_case.choose_patient.ChoosePatientInputData;
import use_case.swap_views.login.SwapToLoginInputBoundary;
import use_case.swap_views.train.SwapToTrainingInputBoundary;

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

        ChoosePatientController choosePatientController = new ChoosePatientController(new ChoosePatientInputBoundary() {
            @Override
            public void execute(ChoosePatientInputData choosePatientInputData) {
                assert true;
            }
        }
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

        SwapToTrainingController swapToTrainingController = new SwapToTrainingController(new SwapToTrainingInputBoundary() {
            @Override
            public void execute() {
                assert true;
            }
        });

        // Instantiate the view
        listOfPatientsView = new ListOfPatientsView(choosePatientController,
                choosePatientViewModel,
                swapToLoginController,
                swapToConversationController,
                loadPatientController,
                swapToDoctorUpdateController,
                swapToTrainingController);
    }

    @Test
    public void testUpperPanel() {
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
}
