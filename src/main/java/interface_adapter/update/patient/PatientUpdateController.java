package interface_adapter.update.patient;

import use_case.update.patient.PatientUpdateInputBoundary;
import use_case.update.patient.PatientUpdateInputData;

public class PatientUpdateController {
    PatientUpdateInputBoundary useCase;

    public PatientUpdateController(PatientUpdateInputBoundary useCase) {
        this.useCase = useCase;
    }

    public void execute(String oldUsername,
                        String newUsername,
                        String password,
                        String repeatPassword,
                        String sex,
                        String gender,
                        double height,
                        double weight,
                        String bloodType) {
        PatientUpdateInputData inputData = new PatientUpdateInputData(oldUsername,
                newUsername,
                password,
                repeatPassword,
                sex,
                gender,
                height,
                weight,
                bloodType);
        useCase.execute(inputData);
    }
}
