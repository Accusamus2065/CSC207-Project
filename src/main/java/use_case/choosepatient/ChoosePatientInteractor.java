package use_case.choosepatient;

import entity.people.User;

import java.util.List;
import java.util.Map;

public class ChoosePatientInteractor implements ChoosePatientInputBoundary {
    final ChoosePatientUserDataAccessInterface userDataAccessObject;
    final ChoosePatientOutputBoundary choosePatientPresenter;

    public ChoosePatientInteractor(ChoosePatientUserDataAccessInterface userDataAccessInterface,
                                   ChoosePatientOutputBoundary choosePatientOutputBoundary) {
        this.userDataAccessObject = userDataAccessInterface;
        this.choosePatientPresenter = choosePatientOutputBoundary;
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
            User user = userDataAccessObject // TODO FIX THIS
            ChoosePatientOutputData choosePatientOutputData = new ChoosePatientOutputData()
            choosePatientPresenter.prepareSuccessView();
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

    @Override
    public List<String> executeGetPatients() {
        return (List)userDataAccessObject.getAccountsPatient().keySet();
        }

    }

}