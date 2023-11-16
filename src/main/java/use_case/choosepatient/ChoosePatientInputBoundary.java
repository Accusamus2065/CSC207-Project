package use_case.choosepatient;

import java.util.List;

public interface ChoosePatientInputBoundary {
    void executeChoosePaitent;
    void executeLogOut();
    void executeChoose();
    List<String> executeGetPatients();

}
