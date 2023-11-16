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


    public void execute(String username, String password, Boolean isDoctor) {
        LoginInputData loginInputData = new LoginInputData(
                username, password, isDoctor);

        choosePatientInteractor.execute(loginInputData);
    }

    public List<String> getPatients(){
        return choosePatientInteractor.executeGetPatients();
    }
}
