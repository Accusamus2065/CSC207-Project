package view;

import interface_adapter.swap_views.chatbot.SwapToDialogflowController;
import interface_adapter.update.patient.PatientUpdateController;
import interface_adapter.update.patient.PatientUpdateViewModel;
import org.junit.Before;
import use_case.swap_views.chatbot.SwapToDialogflowInputBoundary;
import use_case.update.patient.PatientUpdateInputBoundary;
import use_case.update.patient.PatientUpdateInputData;

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


}
