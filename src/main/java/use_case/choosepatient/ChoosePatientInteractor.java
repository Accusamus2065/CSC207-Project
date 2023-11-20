package use_case.choosepatient;


import interface_adapter.choosepatient.ChoosePatientState;

import java.util.List;

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
        try {
            ChoosePatientOutputData choosePatientOutputData = new ChoosePatientOutputData("logout",false);
            choosePatientPresenter.prepareSuccessView(choosePatientOutputData);
        } catch (Exception e) {
            choosePatientPresenter.prepareFailView(e.getMessage());
        }

    }

    public void executeUpdate(){
    try {
        ChoosePatientOutputData choosePatientOutputData = new ChoosePatientOutputData("modify",false);
        choosePatientPresenter.prepareSuccessView(choosePatientOutputData);
    } catch (Exception e) {
        choosePatientPresenter.prepareFailView(e.getMessage());
    }

}


    @Override
    public void executeChoose(String patient) {
        try {
            ChoosePatientOutputData choosePatientOutputData = new ChoosePatientOutputData(patient,
                    "choosePatient",false);
            choosePatientPresenter.prepareSuccessView(choosePatientOutputData);

        } catch (Exception e) {
            choosePatientPresenter.prepareFailView(e.getMessage());
        }
    }

    @Override
    public List<String> executeGetPatients() {
        return (List<String>) userDataAccessObject.getAccountsPatient().keySet();}

}

}