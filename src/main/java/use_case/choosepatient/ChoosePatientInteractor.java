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
    public void executeLogOut(ChoosePatientInputData choosePatientInputData) {
        String username = choosePatientInputData.getUsername();

        try {
            ChoosePatientOutputData choosePatientOutputData = new ChoosePatientOutputData("logout",false, username);
            choosePatientPresenter.prepareSuccessView(choosePatientOutputData);
        } catch (Exception e) {
            choosePatientPresenter.prepareFailView(e.getMessage());
        }

    }

    public void executeUpdate(ChoosePatientInputData choosePatientInputData){
    try {
        String username = choosePatientInputData.getUsername();
        ChoosePatientOutputData choosePatientOutputData = new ChoosePatientOutputData("modify",false, username);
        choosePatientPresenter.prepareSuccessView(choosePatientOutputData);
    } catch (Exception e) {
        choosePatientPresenter.prepareFailView(e.getMessage());
    }

}


    @Override
    public void executeChoose(ChoosePatientInputData choosePatientInputData) {
        String username = choosePatientInputData.getUsername();
        String patient = choosePatientInputData.getPatient();

        try {
            ChoosePatientOutputData choosePatientOutputData = new ChoosePatientOutputData(patient,
                    "choosePatient",false, username);
            choosePatientPresenter.prepareSuccessView(choosePatientOutputData);

        } catch (Exception e) {
            choosePatientPresenter.prepareFailView(e.getMessage());
        }
    }

    @Override
    public List<String> executeGetPatients() {
        return userDataAccessObject.getPatientList();}

}

