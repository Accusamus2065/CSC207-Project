package interface_adapter.choosepatient;

import java.util.List;

public class ChoosePatientState {

    private List<String> patients;
    private String error = null;
    private String username = "";


    public ChoosePatientState(ChoosePatientState copy) {
        this.patients = copy.patients;
        this.error = copy.error;
        this.username = copy.username;
    }

    // Because of the previous copy constructor, the default constructor must be explicit.
    public ChoosePatientState() {}

    public String getUsername() {
        return username;
    }

    public List<String> getPatients() {
        return patients;
    }

    public void setPatients(List<String> patients) {
        this.patients = patients;
    }

    public void setError(String error) { this.error = error;

    }

    public void setUsername(String username) {this.username = username;
    }

    @Override
    public String toString() {
        return "ChoosePatientState{" +
                "patients=" + patients +
                '}';
    }
}

