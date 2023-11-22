package interface_adapter.choosepatient;

import use_case.choosepatient.ChoosePatientInputBoundary;
import use_case.choosepatient.ChoosePatientInputData;
import use_case.login.LoginInputBoundary;
import use_case.login.LoginInputData;

import java.util.List;

public class ChoosePatientController {

    final ChoosePatientInputBoundary choosePatientInteractor;
    public ChoosePatientController(ChoosePatientInputBoundary choosePatientInputBoundary) {
        this.choosePatientInteractor = choosePatientInputBoundary;
    }


    public void executeChoose(ChoosePatientInputData choosePatientInputData) {
        choosePatientInteractor.executeChoose(choosePatientInputData);
    }

    public List<String> executeGetPatients(){
        return choosePatientInteractor.executeGetPatients();
    }

    public void executeUpdate(ChoosePatientInputData choosePatientInputData){
        choosePatientInteractor.executeUpdate(choosePatientInputData);
    }

    public void executeLogout(ChoosePatientInputData choosePatientInputData){
        choosePatientInteractor.executeLogOut(choosePatientInputData);
    }

}
