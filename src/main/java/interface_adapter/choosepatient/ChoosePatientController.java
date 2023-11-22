package interface_adapter.choosepatient;

import use_case.choosepatient.ChoosePatientInputBoundary;
import use_case.choosepatient.ChoosePatientInputData;

import java.util.List;

public class ChoosePatientController {

    final ChoosePatientInputBoundary choosePatientInteractor;
    public ChoosePatientController(ChoosePatientInputBoundary choosePatientInputBoundary) {
        this.choosePatientInteractor = choosePatientInputBoundary;
    }


    public void executeChoose(String username, String patient, String usecase) {
        ChoosePatientInputData choosePatientInputData = new ChoosePatientInputData(username, patient, usecase);
        choosePatientInteractor.executeChoose(choosePatientInputData);
    }

    public void executeUpdate(String username, String patient, String usecase){
        ChoosePatientInputData choosePatientInputData = new ChoosePatientInputData(username, patient, usecase);
        choosePatientInteractor.executeUpdate(choosePatientInputData);
    }

    public void executeLogout(String username, String patient, String usecase){
        ChoosePatientInputData choosePatientInputData = new ChoosePatientInputData(username, patient, usecase);
        choosePatientInteractor.executeLogOut(choosePatientInputData);
    }

    public List<String> executeGetPatients(){
        return choosePatientInteractor.executeGetPatients();
    }

}
