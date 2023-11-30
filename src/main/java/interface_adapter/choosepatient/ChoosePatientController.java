package interface_adapter.choosepatient;

import use_case.choosepatient.ChoosePatientInputBoundary;
import use_case.choosepatient.ChoosePatientInputData;

import java.util.List;

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
