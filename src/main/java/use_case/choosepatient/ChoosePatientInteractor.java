package use_case.choosepatient;


public class ChoosePatientInteractor implements ChoosePatientInputBoundary {
    final ChoosePatientOutputBoundary choosePatientPresenter;

    public ChoosePatientInteractor(ChoosePatientOutputBoundary choosePatientOutputBoundary) {
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

