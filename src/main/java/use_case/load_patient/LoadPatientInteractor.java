package use_case.load_patient;

import use_case.choosepatient.ChoosePatientUserDataAccessInterface;

import java.util.List;

public class LoadPatientInteractor implements LoadPatientInputBoundary {
    final ChoosePatientUserDataAccessInterface userDataAccessObject;

    public LoadPatientInteractor(ChoosePatientUserDataAccessInterface userDataAccessObject) {
        this.userDataAccessObject = userDataAccessObject;
    }

    public List<String> execute(){
        return userDataAccessObject.getPatientList();
    }
}
