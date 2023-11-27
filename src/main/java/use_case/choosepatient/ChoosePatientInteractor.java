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

}

