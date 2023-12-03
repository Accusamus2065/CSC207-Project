package app;

import com.mongodb.MongoException;
import data_access.DAOFacade;
import data_access.DialogflowDAOImpl;
import interface_adapter.ViewManagerModel;
import interface_adapter.chatbot.DialogflowViewModel;
import interface_adapter.chat.refresh.ConversationRefreshViewModel;
import interface_adapter.choose_patient.ChoosePatientViewModel;
import interface_adapter.login.LoginViewModel;
import interface_adapter.signup.SignupViewModel;
import interface_adapter.train.TrainingViewModel;
import interface_adapter.update.doctor.DoctorUpdateViewModel;
import interface_adapter.welcome.WelcomeViewModel;
import view.*;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        // Build the main program window, the main panel containing the
        // various cards, and the layout, and stitch them together.

        // The main application window.
        JFrame application = new JFrame("Benson");
        application.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        CardLayout cardLayout = new CardLayout();

        // The various View objects. Only one view is visible at a time.
        JPanel views = new JPanel(cardLayout);
        application.add(views);

        // This keeps track of and manages which view is currently showing.
        ViewManagerModel viewManagerModel = new ViewManagerModel();
        new ViewManager(views, cardLayout, viewManagerModel);

        DAOFacade entityDataAccessObject;
        try {
            System.out.println("Connecting to MongoDB database...");
            entityDataAccessObject = new DAOFacade();
            System.out.println("Connected to MongoDB database");
        } catch (MongoException e) {
            System.out.println("Couldn't connect to MongoDB database, aborting application...");
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }


        // The data for the views, such as username and password, are in the ViewModels.
        // This information will be changed by a presenter object that is reporting the
        // results from the use case. The ViewModels are observable, and will
        // be observed by the Views.
        WelcomeViewModel welcomeViewModel = new WelcomeViewModel();
        SignupViewModel signupViewModel = new SignupViewModel();
        LoginViewModel loginViewModel = new LoginViewModel();
        DoctorUpdateViewModel docUpdateViewModel = new DoctorUpdateViewModel();
        ConversationRefreshViewModel conversationViewModel = new ConversationRefreshViewModel();
        ChoosePatientViewModel choosePatientViewModel = new ChoosePatientViewModel();
        DialogflowViewModel dialogflowViewModel = new DialogflowViewModel();
        TrainingViewModel  trainingViewModel = new TrainingViewModel();


        WelcomeView welcomeView = WelcomeUseCaseFactory.create(welcomeViewModel, signupViewModel, loginViewModel, viewManagerModel);
        views.add(welcomeView, welcomeView.viewName);
        SignupView signupView = SignupUseCaseFactory.create(viewManagerModel, welcomeViewModel, signupViewModel, loginViewModel, choosePatientViewModel, entityDataAccessObject);
        views.add(signupView, signupView.viewName);
        DoctorUpdateView docUpdateView = DoctorUpdateUseCaseFactory.create(entityDataAccessObject, viewManagerModel, docUpdateViewModel, choosePatientViewModel, loginViewModel);
        views.add(docUpdateView, docUpdateView.viewName);
        LoginView loginView = LoginUseCaseFactory.create(viewManagerModel, welcomeViewModel, dialogflowViewModel, choosePatientViewModel, loginViewModel, signupViewModel, entityDataAccessObject);
        views.add(loginView, loginView.viewName);
        ListOfPatientsView listOfPatientsView = ChoosePatientUseCaseFactory.create(viewManagerModel, conversationViewModel, docUpdateViewModel, choosePatientViewModel, loginViewModel, entityDataAccessObject);
        views.add(listOfPatientsView, listOfPatientsView.viewName);
        DialogflowView dialogflowView = DialogflowUseCaseFactory.create(viewManagerModel, loginViewModel, dialogflowViewModel, conversationViewModel, entityDataAccessObject);
        views.add(dialogflowView, dialogflowView.viewName);
        ConversationView conversationView = ConvoUseCaseFactory.create(viewManagerModel, loginViewModel, conversationViewModel, entityDataAccessObject);
        views.add(conversationView, conversationView.viewName);

        TrainingView trainingView = TrainingUseCaseFactory.create(viewManagerModel, trainingViewModel, new DialogflowDAOImpl());
        views.add(trainingView, trainingView.viewName);

        viewManagerModel.setActiveView(trainingView.viewName);

        viewManagerModel.firePropertyChanged();

        application.pack();
        application.setVisible(true);
    }
}
