package use_case.signup;

import entity.people.*;
import entity.checker.StringCredentialChecker;
import entity.checker.RegexCredentialChecker;

public class SignupInteractor implements SignupInputBoundary {
    final SignupUserDataAccessInterface userDataAccessObject;
    final SignupOutputBoundary signupPresenter;
    final DoctorUserFactory doctorUserFactory;
    final PatientUserFactory patientUserFactory;
    private final StringCredentialChecker credentialChecker = new RegexCredentialChecker();

    public SignupInteractor(SignupUserDataAccessInterface signupDataAccessInterface,
                            SignupOutputBoundary signupOutputBoundary,
                            DoctorUserFactory doctorUserFactory,
                            PatientUserFactory patientUserFactory) {
        this.userDataAccessObject = signupDataAccessInterface;
        this.signupPresenter = signupOutputBoundary;
        this.doctorUserFactory = doctorUserFactory;
        this.patientUserFactory = patientUserFactory;
    }

    @Override
    public void execute(SignupInputData signupInputData) {
        String username = signupInputData.getUsername();
        String password = signupInputData.getPassword();
        String repeatedPassword = signupInputData.getRepeatPassword();
        boolean isDoctor = signupInputData.isDoctor();
        try {
            if (userDataAccessObject.existsByName(isDoctor, username)) {
                signupPresenter.prepareFailView("User already exists.");
            } else if (!password.equals(repeatedPassword)) {
                signupPresenter.prepareFailView("Passwords don't match.");
            } else if (!credentialChecker.validUsername(username)) {
                signupPresenter.prepareFailView("Username is invalid.");
            } else if (!credentialChecker.validPassword(password)) {
                signupPresenter.prepareFailView("Password requires a digit and a letter, be more than 5 characters, and cannot have any other characters.");
            } else {
                if (isDoctor) {
                    IDoctor doctor = doctorUserFactory.create(username, password);
                    userDataAccessObject.save(doctor);
                } else {
                    IPatient patient = patientUserFactory.create(username, password);
                    userDataAccessObject.save(patient);
                }
                SignupOutputData signupOutputData = new SignupOutputData(username, false);
                signupPresenter.prepareSuccessView(signupOutputData);
            }
        } catch (Exception e) {
            signupPresenter.prepareFailView(e.getMessage());
        }
    }
}
