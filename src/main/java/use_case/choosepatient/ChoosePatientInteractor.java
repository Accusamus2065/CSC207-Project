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
    public void execute(ChoosePatientInputData choosePatientInputData) {
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

    public List<String> executeGetPatients() {
        return userDataAccessObject.getPatientList();}

}

