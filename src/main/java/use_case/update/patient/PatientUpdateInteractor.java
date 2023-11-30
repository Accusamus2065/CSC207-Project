package use_case.update.patient;

import entity.checker.CredentialChecker;
import entity.checker.CredentialCheckerFacade;
import entity.people.PatientUserFactory;
import entity.people.IPatient;
import entity.checker.StringCredentialChecker;
import entity.checker.RegexCredentialChecker;


public class PatientUpdateInteractor implements PatientUpdateInputBoundary {
    final PatientUpdateUserDataAccessInterface userDataAccessObject;
    final PatientUpdateOutputBoundary userPresenter;
    final PatientUserFactory userFactory;
    private final CredentialChecker credentialChecker = new CredentialCheckerFacade();

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
            } else if (!credentialChecker.validUsername(patientUpdateInputData.getNewUsername())) {
                userPresenter.prepareFailView("Username is invalid.");
            } else if (!credentialChecker.validPassword(patientUpdateInputData.getPassword())) {
                userPresenter.prepareFailView("Password requires a digit and a letter, be more than 5 characters, and cannot have any other characters.");
            } else if (!credentialChecker.validSex(patientUpdateInputData.getSex())) {
                userPresenter.prepareFailView("Sex must be either 'M', 'F' or 'O'.");
            } else if (!credentialChecker.validGender(patientUpdateInputData.getGender())) {
                userPresenter.prepareFailView("Gender is Empty");
            } else if (!credentialChecker.validWeight(patientUpdateInputData.getWeight())) {
                userPresenter.prepareFailView("Weight is invalid");
            } else if (!credentialChecker.validHeight(patientUpdateInputData.getHeight())) {
                userPresenter.prepareFailView("Height is invalid");
            } else if (!credentialChecker.validBloodType(patientUpdateInputData.getBloodType())) {
                userPresenter.prepareFailView("Blood Type must be (A, B, AB, O with + or -)");
            } else {
                IPatient patient = userFactory.create(patientUpdateInputData.getNewUsername(),
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
