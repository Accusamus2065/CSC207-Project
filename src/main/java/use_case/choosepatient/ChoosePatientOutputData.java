package use_case.choosepatient;

import interface_adapter.choosepatient.ChoosePatientState;

public class ChoosePatientOutputData {

    private final String patient;
    private final String usecase;
    private final boolean useCaseFailed;


    public ChoosePatientOutputData(String patient, String usecase, boolean useCaseFailed) {
        this.patient = patient;
        this.usecase = usecase;
        this.useCaseFailed = useCaseFailed;
    }

    public ChoosePatientOutputData(String usecase, boolean useCaseFailed){
        this.patient = "";
        this.usecase = usecase;
        this.useCaseFailed = useCaseFailed;
    }

    public String getPatient() {
        return patient;
    }

    public String getUsecase() {return usecase; }


    public boolean isUseCaseFailed() {
        return useCaseFailed;
    }
}
