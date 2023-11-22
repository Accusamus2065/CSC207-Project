package interface_adapter.choosepatient;

import use_case.choosepatient.ChoosePatientInputBoundary;
import use_case.choosepatient.ChoosePatientInputData;

import java.util.List;

public class ChoosePatientController {

    final ChoosePatientInputBoundary choosePatientInteractor;
    public ChoosePatientController(ChoosePatientInputBoundary choosePatientInputBoundary) {
        this.choosePatientInteractor = choosePatientInputBoundary;
    }


    public void executeChoose(String username, String patient) {
        ChoosePatientInputData choosePatientInputData = new ChoosePatientInputData(username, patient);
        choosePatientInteractor.executeChoose(choosePatientInputData);
    }

    public void executeUpdate(String username){
        ChoosePatientInputData choosePatientInputData = new ChoosePatientInputData(username);
        choosePatientInteractor.executeUpdate(choosePatientInputData);
    }

    public void executeLogout(String username){
        ChoosePatientInputData choosePatientInputData = new ChoosePatientInputData(username);
        choosePatientInteractor.executeLogOut(choosePatientInputData);
    }

    public List<String> executeGetPatients(){
        return choosePatientInteractor.executeGetPatients();
    }

}
