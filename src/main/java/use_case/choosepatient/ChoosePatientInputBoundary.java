package use_case.choosepatient;

import java.util.List;

public interface ChoosePatientInputBoundary {
    void executeLogOut(ChoosePatientInputData choosePatientInputData);
    void executeChoose(ChoosePatientInputData choosePatientInputData);
    List<String> executeGetPatients();

    void executeUpdate(ChoosePatientInputData choosePatientInputData);
}
