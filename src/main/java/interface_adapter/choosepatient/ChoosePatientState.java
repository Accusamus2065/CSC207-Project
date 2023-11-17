package interface_adapter.choosepatient;

import java.util.List;

public class ChoosePatientState {

    private List<String> patients;


    public ChoosePatientState(ChoosePatientState copy) {
        this.patients = copy.patients;
    }

    // Because of the previous copy constructor, the default constructor must be explicit.
    public ChoosePatientState() {}

    @Override
    public String toString() {
        return "ChoosePatientState{" +
                "patients=" + patients +
                '}';
    }

    public List<String> getPatients() {
        return patients;
    }

    public void setPatients(List<String> patients) {
        this.patients = patients;
    }
}
