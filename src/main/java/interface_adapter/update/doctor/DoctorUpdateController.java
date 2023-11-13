package interface_adapter.update.doctor;

import use_case.update.doctor.DoctorUpdateInputBoundary;
import use_case.update.doctor.DoctorUpdateInputData;

public class DoctorUpdateController {
    final DoctorUpdateInputBoundary useCase;

    public DoctorUpdateController(DoctorUpdateInputBoundary useCase) {
        this.useCase = useCase;
    }

    public void execute(String oldUsername,
                        String newUsername,
                        String password,
                        String repeatPassword,
                        String specialty,
                        String degree) {
        DoctorUpdateInputData inputData = new DoctorUpdateInputData(
                oldUsername,
                newUsername,
                password,
                repeatPassword,
                specialty,
                degree
        );
        useCase.execute(inputData);
    }
}
