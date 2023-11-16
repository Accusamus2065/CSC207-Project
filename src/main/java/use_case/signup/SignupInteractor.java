package use_case.signup;

import entity.people.*;

public class SignupInteractor implements SignupInputBoundary {
    final SignupUserDataAccessInterface userDataAccessObject;
    final SignupOutputBoundary signupPresenter;
    final UserFactory userFactory;

    public SignupInteractor(SignupUserDataAccessInterface signupDataAccessInterface,
                            SignupOutputBoundary signupOutputBoundary,
                            UserFactory userFactory) {
        this.userDataAccessObject = signupDataAccessInterface;
        this.signupPresenter = signupOutputBoundary;
        this.userFactory = userFactory;
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
            } else {
                User user = userFactory.create(username, password);
                if (isDoctor) {
                    userDataAccessObject.save((IDoctor) user);
                } else {
                    userDataAccessObject.save((IPatient) user);
                }
                SignupOutputData signupOutputData = new SignupOutputData(username, false);
                signupPresenter.prepareSuccessView(signupOutputData);
            }
        } catch (Exception e) {
            signupPresenter.prepareFailView(e.getMessage());
        }
    }
}
