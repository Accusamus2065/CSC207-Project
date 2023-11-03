package use_case.welcome.signup;

import org.junit.Test;
import use_case.welcome.WelcomeInputData;
import use_case.welcome.WelcomeOutputData;

import static org.junit.Assert.*;

public class WelcomeSignupInteractorTest {

    @Test
    public void isDoctorSignupTest() {
        WelcomeSignupOutputBoundary successPresenter = new WelcomeSignupOutputBoundary() {

            @Override
            public void swapViews(WelcomeOutputData welcomeOutputData) {
                assertTrue(welcomeOutputData.isDoctor());
            }
        };

        WelcomeInputData welcomeInputData = new WelcomeInputData(true);
        WelcomeSignupInputBoundary interactor = new WelcomeSignupInteractor(successPresenter);
        interactor.execute(welcomeInputData);
    }

    @Test
    public void notIsDoctorSignupTest() {
        WelcomeSignupOutputBoundary successPresenter = new WelcomeSignupOutputBoundary() {
            @Override
            public void swapViews(WelcomeOutputData welcomeOutputData) {
                assertFalse(welcomeOutputData.isDoctor());
            }
        };

        WelcomeInputData welcomeInputData = new WelcomeInputData(false);
        WelcomeSignupInputBoundary interactor = new WelcomeSignupInteractor(successPresenter);
        interactor.execute(welcomeInputData);
    }
}