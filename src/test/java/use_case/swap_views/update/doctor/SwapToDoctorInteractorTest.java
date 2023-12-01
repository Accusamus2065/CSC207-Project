package use_case.swap_views.update.doctor;


import org.junit.Test;

public class SwapToDoctorInteractorTest {
    @Test
    public void presenterCalledTest() {
        SwapToDoctorUpdateOutputBoundary swapPresenter = username -> {
            assert true;
        };

        SwapToDoctorUpdateInteractor interactor = new SwapToDoctorUpdateInteractor(swapPresenter);
        interactor.execute("User1");
    }
}