package use_case.login;

import entity.people.IDoctor;
import entity.people.IPatient;

public class LoginInteractor implements LoginInputBoundary {
    final LoginUserDataAccessInterface userDataAccessObject;
    final LoginOutputBoundary loginPresenter;

    public LoginInteractor(LoginUserDataAccessInterface userDataAccessInterface,
                           LoginOutputBoundary loginOutputBoundary) {
        this.userDataAccessObject = userDataAccessInterface;
        this.loginPresenter = loginOutputBoundary;
    }

    @Override
    public void execute(LoginInputData loginInputData) {
        String username = loginInputData.getUsername();
        String password = loginInputData.getPassword();
        boolean isDoctor = loginInputData.getIsDoctor();
        try {
            if (!userDataAccessObject.existsByName(isDoctor, username)) {
                loginPresenter.prepareFailView(username + ": Account does not exist.");
            } else {
                if (isDoctor) {
                    IDoctor doctor = userDataAccessObject.getDoctor(username);
                    String pwd = doctor.getPassword();
                    if (!password.equals(pwd)) {
                        loginPresenter.prepareFailView("Incorrect password for " + username + ".");
                    } else {
                        LoginOutputData loginOutputData = new LoginOutputData(doctor.getUsername(), false);
                        loginPresenter.prepareSuccessView(loginOutputData);
                    }
                } else {
                    IPatient patient = userDataAccessObject.getPatient(username);
                    String pwd = patient.getPassword();
                    if (!password.equals(pwd)) {
                        loginPresenter.prepareFailView("Incorrect password for " + username + ".");
                    } else {
                        LoginOutputData loginOutputData = new LoginOutputData(patient.getUsername(), false);
                        loginPresenter.prepareSuccessView(loginOutputData);
                    }
                }
            }
        } catch (Exception e) {
            loginPresenter.prepareFailView(e.getMessage());
        }
    }
}