package interface_adapter.load_patients;

import use_case.load_patient.LoadPatientInputBoundary;

import java.util.List;

public class LoadPatientsController {
    LoadPatientInputBoundary loadPatientInteractor;

    public LoadPatientsController(LoadPatientInputBoundary loadPatientInteractor) {
        this.loadPatientInteractor= loadPatientInteractor;
    }

    public List<String> execute() {return loadPatientInteractor.execute();}
}
