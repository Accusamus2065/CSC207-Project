package use_case.choosepatient;

public class ChoosePatientInputData {

    private final String username;
    private final String patient;
    private final String usecase;

    public ChoosePatientInputData(String username, String patient, String usecase) {
        this.username = username;
        this.patient = patient;
        this.usecase = usecase;
    }

    String getUsername() {
        return username;
    }
    public String getPatient() {
        return patient;
    }
    public String getUsecase(){ return usecase; }
}
