package interface_adapter.login;

import use_case.login.LoginInputData;
import use_case.login.LoginInputBoundary;

public class LoginController {

    final LoginInputBoundary loginUseCaseInteractor;
    public LoginController(LoginInputBoundary loginUseCaseInteractor) {
        this.loginUseCaseInteractor = loginUseCaseInteractor;
    }


    public void execute(String username, String password, Boolean isDoctor) {
        LoginInputData loginInputData = new LoginInputData(
                username, password, isDoctor);

        loginUseCaseInteractor.execute(loginInputData);
    }
}
