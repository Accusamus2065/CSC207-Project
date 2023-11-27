package use_case.load_patient;

import data_access.DAOFacade;
import entity.people.IPatient;
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
