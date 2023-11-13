package use_case.update.patient;

import entity.people.PatientUserFactory;
import entity.people.IPatient;


public class PatientUpdateInteractor implements PatientUpdateInputBoundary {
    final PatientUpdateUserDataAccessInterface userDataAccessObject;
    final PatientUpdateOutputBoundary userPresenter;
    final PatientUserFactory userFactory;

    public PatientUpdateInteractor(PatientUpdateUserDataAccessInterface userDataAccessInterface,
                                   PatientUpdateOutputBoundary userPresenter,
                                   PatientUserFactory userFactory) {
        this.userDataAccessObject = userDataAccessInterface;
        this.userPresenter = userPresenter;
        this.userFactory = userFactory;
    }

    @Override
    public void execute(PatientUpdateInputData patientUpdateInputData) {
        try {
            if (userDataAccessObject.existsByName(false, patientUpdateInputData.getNewUsername())) {
                userPresenter.prepareFailView("User already exists.");
            } else if (!patientUpdateInputData.getPassword().equals(patientUpdateInputData.getRepeatPassword())) {
                userPresenter.prepareFailView("Passwords don't match.");
            } else {
                IPatient patient = (IPatient) userFactory.create(patientUpdateInputData.getNewUsername(),
                        patientUpdateInputData.getPassword(),
                        patientUpdateInputData.getSex(),
                        patientUpdateInputData.getGender(),
                        patientUpdateInputData.getHeight(),
                        patientUpdateInputData.getWeight(),
                        patientUpdateInputData.getBloodType());
                userDataAccessObject.update(patientUpdateInputData.getOldUsername(), patient);
                PatientUpdateOutputData outputData = new PatientUpdateOutputData(patient.getUsername(), false);
                userPresenter.prepareSuccessView(outputData);
            }
        } catch (Exception e) {
            userPresenter.prepareFailView(e.getMessage());
        }
    }
}
