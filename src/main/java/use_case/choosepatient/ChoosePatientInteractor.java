package use_case.choosepatient;


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

    }

    @Override
    public void executeChoose(String patient) {

        try {
            ChoosePatientOutputData choosePatientOutputData = new ChoosePatientOutputData(patient, false);
            choosePatientPresenter.prepareSuccessView(choosePatientOutputData);

        } catch (Exception e) {
            choosePatientPresenter.prepareFailView(e.getMessage());
        }
    }

    @Override
    public List<String> executeGetPatients() {
        List<String> strings = (List<String>) userDataAccessObject.getAccountsPatient().keySet();
        return  strings;}

}

}