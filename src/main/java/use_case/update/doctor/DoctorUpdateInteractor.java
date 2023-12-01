package use_case.update.doctor;

import entity.people.DoctorUserFactory;
import entity.people.IDoctor;
import entity.checker.StringCredentialChecker;
import entity.checker.RegexCredentialChecker;


public class DoctorUpdateInteractor implements DoctorUpdateInputBoundary {
    final DoctorUpdateUserDataAccessInterface userDataAccessObject;
    final DoctorUpdateOutputBoundary userPresenter;
    final DoctorUserFactory userFactory;
    private final StringCredentialChecker credentialChecker = new RegexCredentialChecker();

    public DoctorUpdateInteractor(DoctorUpdateUserDataAccessInterface userDataAccessInterface,
                                  DoctorUpdateOutputBoundary userPresenter,
                                  DoctorUserFactory userFactory) {
        this.userDataAccessObject = userDataAccessInterface;
        this.userPresenter = userPresenter;
        this.userFactory = userFactory;
    }

    @Override
    public void execute(DoctorUpdateInputData doctorUpdateInputData) {
        try {
            if (userDataAccessObject.existsByName(true, doctorUpdateInputData.getNewUsername())) {
                userPresenter.prepareFailView("User already exists.");
            } else if (!doctorUpdateInputData.getPassword().equals(doctorUpdateInputData.getRepeatPassword())) {
                userPresenter.prepareFailView("Passwords don't match.");
            } else if (!credentialChecker.validUsername(doctorUpdateInputData.getNewUsername())) {
                userPresenter.prepareFailView("Username is invalid.");
            } else if (!credentialChecker.validPassword(doctorUpdateInputData.getPassword())) {
                userPresenter.prepareFailView("Password requires a digit and a letter, be more than 5 characters, and cannot have any other characters.");
            } else if (!credentialChecker.validSpecialty(doctorUpdateInputData.getSpecialty())) {
                userPresenter.prepareFailView("Specialty is Empty.");
            } else if (!credentialChecker.validDegree(doctorUpdateInputData.getDegree())) {
                userPresenter.prepareFailView("Degree is Empty.");
            } else {
                IDoctor doctor = userFactory.create(
                        doctorUpdateInputData.getNewUsername(),
                        doctorUpdateInputData.getPassword(),
                        doctorUpdateInputData.getDegree(),
                        doctorUpdateInputData.getSpecialty()
                );
                userDataAccessObject.update(doctorUpdateInputData.getOldUsername(), doctor);
                DoctorUpdateOutputData outputDate = new DoctorUpdateOutputData(doctor.getUsername(), false);
                userPresenter.prepareSuccessView(outputDate);
            }
        } catch (Exception e) {
            userPresenter.prepareFailView(e.getMessage());
        }
    }
}
