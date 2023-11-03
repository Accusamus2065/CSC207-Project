package use_case.welcome.login;

import org.junit.Test;
import use_case.welcome.WelcomeInputData;
import use_case.welcome.WelcomeOutputData;

import static org.junit.Assert.*;

public class WelcomeLoginInteractorTest {
    @Test
    public void isDoctorLoginTest() {
        WelcomeLoginOutputBoundary successPresenter = new WelcomeLoginOutputBoundary() {
            @Override
            public void swapViews(WelcomeOutputData welcomeOutputData) {
                assertTrue(welcomeOutputData.isDoctor());
            }
        };

        WelcomeInputData inputData = new WelcomeInputData(true);
        WelcomeLoginInteractor interactor = new WelcomeLoginInteractor(successPresenter);
        interactor.execute(inputData);
    }

    @Test
    public void notIsDoctorLoginTest() {
        WelcomeLoginOutputBoundary successPresenter = new WelcomeLoginOutputBoundary() {
            @Override
            public void swapViews(WelcomeOutputData welcomeOutputData) {
                assertFalse(welcomeOutputData.isDoctor());
            }
        };

        WelcomeInputData inputData = new WelcomeInputData(false);
        WelcomeLoginInteractor interactor = new WelcomeLoginInteractor(successPresenter);
        interactor.execute(inputData);
    }
}