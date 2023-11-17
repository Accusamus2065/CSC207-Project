package use_case.update.doctor;

import entity.people.DoctorUserFactory;
import entity.people.IDoctor;


public class DoctorUpdateInteractor implements DoctorUpdateInputBoundary {
    final DoctorUpdateUserDataAccessInterface userDataAccessObject;
    final DoctorUpdateOutputBoundary userPresenter;
    final DoctorUserFactory userFactory;

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
            } else {
                IDoctor doctor = userFactory.create(doctorUpdateInputData.getNewUsername(),
                        doctorUpdateInputData.getPassword(),
                        doctorUpdateInputData.getSpecialty(),
                        doctorUpdateInputData.getDegree());
                userDataAccessObject.update(doctorUpdateInputData.getOldUsername(), doctor);
                DoctorUpdateOutputData outputDate = new DoctorUpdateOutputData(doctor.getUsername(), false);
                userPresenter.prepareSuccessView(outputDate);
            }
        } catch (Exception e) {
            userPresenter.prepareFailView(e.getMessage());
        }
    }
}

