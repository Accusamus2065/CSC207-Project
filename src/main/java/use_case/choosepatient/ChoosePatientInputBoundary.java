package use_case.choosepatient;

import java.util.List;

public interface ChoosePatientInputBoundary {
    void executeLogOut();
    void executeChoose(String Patient);
    List<String> executeGetPatients();

    void executeUpdate();
}
