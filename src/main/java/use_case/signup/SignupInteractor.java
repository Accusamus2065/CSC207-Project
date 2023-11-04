package use_case.signup;


import entity.people.IDoctor;
import entity.people.IPatient;
import entity.people.UserFactory;


public class SignupInteractor implements SignupInputBoundary {
    final SignupUserDataAccessInterface userDataAccessObject;
    final SignupOutputBoundary userPresenter;
    final UserFactory userFactory;

    public SignupInteractor(SignupUserDataAccessInterface signupDataAccessInterface,
                            SignupOutputBoundary signupOutputBoundary,
                            UserFactory userFactory) {
        this.userDataAccessObject = signupDataAccessInterface;
        this.userPresenter = signupOutputBoundary;
        this.userFactory = userFactory;
    }

    @Override
    public void execute(SignupInputData signupInputData) {
        if (userDataAccessObject.existsByName(signupInputData.isDoctor(), signupInputData.getUsername())) {
            userPresenter.prepareFailView("User already exists.");
        } else if (!signupInputData.getPassword().equals(signupInputData.getRepeatPassword())) {
            userPresenter.prepareFailView("Passwords don't match.");
        } else {
            if (signupInputData.isDoctor()) {
                IDoctor doctor = (IDoctor) userFactory.create(signupInputData.getUsername(), signupInputData.getPassword());
                userDataAccessObject.save(doctor);
                SignupOutputData signupOutputData = new SignupOutputData(doctor.getUsername(), false,
                        signupInputData.isDoctor());
                userPresenter.prepareSuccessView(signupOutputData);
            } else {
                IPatient patient = (IPatient) userFactory.create(signupInputData.getUsername(), signupInputData.getPassword());
                userDataAccessObject.save(patient);
                SignupOutputData signupOutputData = new SignupOutputData(patient.getUsername(), false,
                        signupInputData.isDoctor());
                userPresenter.prepareSuccessView(signupOutputData);
            }
        }
    }
}