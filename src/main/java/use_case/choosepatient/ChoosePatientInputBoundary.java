package use_case.choosepatient;

import java.util.List;

public interface ChoosePatientInputBoundary {
    void execute(ChoosePatientInputData choosePatientInputData);
    void executeUpdate(ChoosePatientInputData choosePatientInputData);
}
