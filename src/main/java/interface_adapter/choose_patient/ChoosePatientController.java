package interface_adapter.choose_patient;

import use_case.choose_patient.ChoosePatientInputBoundary;
import use_case.choose_patient.ChoosePatientInputData;

public class ChoosePatientController {

    final ChoosePatientInputBoundary choosePatientInteractor;
    public ChoosePatientController(ChoosePatientInputBoundary choosePatientInputBoundary) {
        this.choosePatientInteractor = choosePatientInputBoundary;
    }


    public void execute(String username, String patient) {
        ChoosePatientInputData choosePatientInputData = new ChoosePatientInputData(username, patient);
        choosePatientInteractor.execute(choosePatientInputData);
    }


}
