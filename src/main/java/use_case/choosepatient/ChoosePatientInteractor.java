package use_case.choosepatient;

import entity.people.User;

public class ChoosePatientInteractor implements ChoosePatientInputBoundary {
    final ChoosePatientUserDataAccessInterface userDataAccessObject;
    final ChoosePatientOutputBoundary loginPresenter;

    public ChoosePatientInteractor(ChoosePatientUserDataAccessInterface userDataAccessInterface,
                                   ChoosePatientOutputBoundary loginOutputBoundary) {
        this.userDataAccessObject = userDataAccessInterface;
        this.loginPresenter = loginOutputBoundary;
    }

    @Override
    public void executeLogOut() {

    }

    @Override
    public void executeChoose() {
        String username = loginInputData.getUsername();
        String password = loginInputData.getPassword();
        Boolean isDoctor = loginInputData.getIsDoctor();
        try {
            if (!userDataAccessObject.existsByName(isDoctor, username)) {
                loginPresenter.prepareFailView(username + ": Account does not exist.");
            } else {
                String pwd = userDataAccessObject.get(username).getPassword();
                if (!password.equals(pwd)) {
                    loginPresenter.prepareFailView("Incorrect password for " + username + ".");
                } else {

                    User user = userDataAccessObject.get(loginInputData.getUsername());

                    ChoosePatientOutputData loginOutputData = new ChoosePatientOutputData(user.getUsername(), false);
                    loginPresenter.prepareSuccessView(loginOutputData);
                }
            }
        } catch (Exception e) {
            loginPresenter.prepareFailView(e.getMessage());
        }
    }
}