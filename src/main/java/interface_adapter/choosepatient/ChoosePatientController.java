package interface_adapter.choosepatient;

import use_case.login.LoginInputBoundary;
import use_case.login.LoginInputData;

public class ChoosePatientController {

    final LoginInputBoundary loginUseCaseInteractor;
    public ChoosePatientController(LoginInputBoundary loginUseCaseInteractor) {
        this.loginUseCaseInteractor = loginUseCaseInteractor;
    }


    public void execute(String username, String password, Boolean isDoctor) {
        LoginInputData loginInputData = new LoginInputData(
                username, password, isDoctor);

        loginUseCaseInteractor.execute(loginInputData);
    }
}
