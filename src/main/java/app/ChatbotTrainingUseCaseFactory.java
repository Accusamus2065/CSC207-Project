//
//package app;
//
//import data_access.DAOFacade;
//import interface_adapter.ViewManagerModel;
//import interface_adapter.chat.refresh.ConversationRefreshViewModel;
//import interface_adapter.choose_patient.ChoosePatientController;
//import interface_adapter.choose_patient.ChoosePatientPresenter;
//import interface_adapter.choose_patient.ChoosePatientViewModel;
//import interface_adapter.swap_views.load_patients.LoadPatientsController;
//import interface_adapter.swap_views.update.doctor.SwapToDoctorUpdateController;
//import interface_adapter.swap_views.update.doctor.SwapToDoctorUpdatePresenter;
//import interface_adapter.swap_views.welcome.SwapToWelcomeController;
//import interface_adapter.swap_views.welcome.SwapToWelcomePresenter;
//import interface_adapter.train.TrainingController;
//import interface_adapter.update.doctor.DoctorUpdateViewModel;
//import interface_adapter.welcome.WelcomeViewModel;
//import use_case.choose_patient.ChoosePatientInputBoundary;
//import use_case.choose_patient.ChoosePatientInteractor;
//import use_case.choose_patient.ChoosePatientOutputBoundary;
//import use_case.choose_patient.ChoosePatientUserDataAccessInterface;
//import use_case.load_patient.LoadPatientInputBoundary;
//import use_case.load_patient.LoadPatientInteractor;
//import use_case.swap_views.update.doctor.SwapToDoctorUpdateInputBoundary;
//import use_case.swap_views.update.doctor.SwapToDoctorUpdateInteractor;
//import use_case.swap_views.update.doctor.SwapToDoctorUpdateOutputBoundary;
//import use_case.swap_views.welcome.SwapToWelcomeInputBoundary;
//import use_case.swap_views.welcome.SwapToWelcomeInteractor;
//import use_case.swap_views.welcome.SwapToWelcomeOutputBoundary;
//import view.ListOfPatientsView;
//
//public class ChatbotTrainingUseCaseFactory {
//
//    /* Prevent instantiation. */
//    public ChatbotTrainingUseCaseFactory() {
//    }
//
//    public static TrainningView create(ViewManagerModel viewManagerModel, TrainingViewModel trainingViewModel,
//                                            DAOFacade dao) {
//        TrainingController controller = createTrainingUseCase(viewManagerModel, trainingViewModel);
//        return new TrainingView(controller, trainingViewModel);
//    }
//
//    private static TrainingController createTrainingUseCase(ViewManagerModel viewManagerModel,
//                                                            TrainingViewModel trainingViewModel) {
//        TrainingOutputBoundary presenter = new TrainingPresenter(viewManagerModel, trainingViewModel);
//
//        TrainingInputBoundary interactor = new TrainingInteractor(presenter);
//        return new TrainingController(interactor);
//    }
//}
