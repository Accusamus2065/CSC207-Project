package use_case.choose_patient;


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
            ChoosePatientOutputData choosePatientOutputData = new ChoosePatientOutputData(username, patient,
                    false);
            choosePatientPresenter.prepareSuccessView(choosePatientOutputData);

        }
        catch (Exception e) {choosePatientPresenter.prepareFailView(e.getMessage());}
    }

}

