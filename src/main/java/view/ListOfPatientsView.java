package view;

import interface_adapter.choosepatient.ChoosePatientViewModel;
import interface_adapter.chat.ConversationViewModel;
import interface_adapter.choosepatient.ChoosePatientController;
import interface_adapter.choosepatient.ChoosePatientState;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class ListOfPatientsView {
    private JFrame frame;
    private JPanel panel;
    private JButton logOutButton;
    private JLabel listOfPatientsLabel;
    private JButton modifyButton;

    private final ChoosePatientController choosePatientController;
    private final ChoosePatientViewModel choosePatientViewModel;

    public ListOfPatientsView(ChoosePatientController choosePatientController, ChoosePatientViewModel choosePatientViewModel) {
        this.choosePatientController = choosePatientController;
        this.choosePatientViewModel = choosePatientViewModel;
        // Create and do settings for frame
        frame = new JFrame();
        frame.setTitle(ChoosePatientViewModel.TITLE_LABEL);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setSize(ChoosePatientViewModel.FRAME_WIDTH_SIZE, ChoosePatientViewModel.FRAME_HEIGHT_SIZE);
        frame.setLocationRelativeTo(null);

        // Create and do settings for main panel
        panel = new JPanel();
        panel.setLayout(new BorderLayout());
//        panel.setLayout(new FlowLayout(FlowLayout.CENTER, 150, 20));
        panel.setBackground(Color.lightGray);
        panel.setPreferredSize(ChoosePatientViewModel.PANEL_DIMENSION);
        frame.add(panel, BorderLayout.CENTER);

        // Create the upper sub-panel that will contain the button to log out, listOfPatients label, and modify button
        JPanel upperPanel = new JPanel();
        upperPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 50, 20));
        upperPanel.setBackground(Color.lightGray);
        panel.add(upperPanel, BorderLayout.NORTH);

        // Create the button for logging out of the profile
        logOutButton = new JButton(ChoosePatientViewModel.LOGOUT_BUTTON_LABEL);
        logOutButton.setFont(ChoosePatientViewModel.BUTTON_FONT);
        logOutButton.setFocusable(false);
        logOutButton.addActionListener(
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        if(e.getSource().equals(modifyButton)){
                            ChoosePatientState currentState = choosePatientViewModel.getState();

                            choosePatientController.executeLogout(currentState.getUsername());
                        }
                    }});
        logOutButton.setPreferredSize(ChoosePatientViewModel.BUTTON_DIMENSION);
        upperPanel.add(logOutButton);

        // Create and add label of List of Patients to the upper panel
        listOfPatientsLabel = new JLabel(ChoosePatientViewModel.MAIN_LABEL);
        listOfPatientsLabel.setFont(ChoosePatientViewModel.MAIN_LABEL_FONT);
        upperPanel.add(listOfPatientsLabel);

        // Create the button for modifying the profile
        modifyButton = new JButton(ChoosePatientViewModel.MODIFY_BUTTON_LABEL);
        modifyButton.setFont(ChoosePatientViewModel.BUTTON_FONT);
        modifyButton.setFocusable(false);
        modifyButton.addActionListener(
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        if(e.getSource().equals(modifyButton)){
                            ChoosePatientState currentState = choosePatientViewModel.getState();

                            choosePatientController.executeUpdate(currentState.getUsername());
                        }
                    }});
        // USING WRONG BUTTON DIMENSINO RN
        modifyButton.setPreferredSize(ChoosePatientViewModel.BUTTON_DIMENSION);  //TODO NEED BUTTON DIMENSION IN CONVOVIEWMODEL
        upperPanel.add(modifyButton);

        // Create the sub-panel in the middle that contains the list of buttons linking to the patients
        JPanel midPanel = new JPanel();
        midPanel.setLayout(new BoxLayout(midPanel, BoxLayout.Y_AXIS));
        midPanel.setBackground(Color.lightGray);

        // get a list of patients
        List<String> patients = choosePatientController.executeGetPatients();
        // Add the buttons that will link doctor to the chat with the patients
        for(String p: patients) {
            JButton button = new JButton(p);
            button.setFont(ChoosePatientViewModel.BUTTON_FONT);
            button.setBackground(Color.white);
            button.setFocusable(false);
            button.setAlignmentX(Component.CENTER_ALIGNMENT);
            button.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    if(e.getSource().equals(modifyButton)){
                        ChoosePatientState currentState = choosePatientViewModel.getState();

                        choosePatientController.executeUpdate(currentState.getUsername());
                    }
                }});
            midPanel.add(button);
        }

        // Add midPanel to a JScrollPane
        JScrollPane scrollPane = new JScrollPane(midPanel);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        panel.add(scrollPane);
    }

    public void show() {
        frame.setVisible(true);
    }
}